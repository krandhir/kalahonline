package com.kgc.nl.kalah.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ReflectionUtils;
import com.kgc.nl.kalah.model.Game;
import com.kgc.nl.kalah.model.Player;
import com.kgc.nl.kalah.service.IKalahService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class KalahServiceImplTest {

  @Autowired
  private IKalahService service;

  private Game gameInitial;
  private Game gameFinishedWinnerSouth;
  private Game gameFinishedWinnerNorth;
  private Game gameNorthMovedFirst;
  private Game gameSouthMovedFirst;
  private Game gameTurnNorth;
  private Game gameTurnSouth;

  @BeforeEach
  void init() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    this.gameInitial = new Game();
    this.gameFinishedWinnerSouth = new Game();
    this.gameFinishedWinnerNorth = new Game();
    this.gameNorthMovedFirst = new Game();
    this.gameSouthMovedFirst = new Game();
    this.gameTurnNorth = new Game();
    this.gameTurnSouth = new Game();

    final Method resetBoard = openMethodForTest(service.getClass().getDeclaredMethod("resetBoard", Game.class));
    final Method distributeStones =
        openMethodForTest(service.getClass().getDeclaredMethod("distributeStones", Game.class, int.class));

    resetBoard.invoke(this.service, this.gameFinishedWinnerSouth);
    this.gameFinishedWinnerSouth.getBoard().getPit(Player.PLAYER_NORTH.getPosition()).setStoneCount(10);
    this.gameFinishedWinnerSouth.getBoard().getPit(Player.PLAYER_SOUTH.getPosition()).setStoneCount(62);
    resetBoard.invoke(this.service, this.gameFinishedWinnerNorth);
    this.gameFinishedWinnerNorth.getBoard().getPit(1).setStoneCount(1);
    this.gameFinishedWinnerNorth.getBoard().getPit(Player.PLAYER_NORTH.getPosition()).setStoneCount(39);
    this.gameFinishedWinnerNorth.getBoard().getPit(Player.PLAYER_SOUTH.getPosition()).setStoneCount(32);

    distributeStones.invoke(this.service, this.gameNorthMovedFirst, 1);
    distributeStones.invoke(this.service, this.gameSouthMovedFirst, 10);
    distributeStones.invoke(this.service, this.gameTurnNorth, 1);
    distributeStones.invoke(this.service, this.gameTurnSouth, 8);
  }

  @Test
  @DirtiesContext
  void testCreateGame() {
    final Game game = this.service.createGame();

    assertNotNull(game);
  }

  @Test
  @DirtiesContext
  void testPlay() {
    final Game game = this.service.createGame();
    this.service.play(game.getId(), 6);

    assertEquals(Player.PLAYER_SOUTH, game.getTurn());
    assertNull(game.getWinner());
    assertEquals(31, game.getBoard().getStoneCount(Player.PLAYER_NORTH, true));
  }

  @Test
  void testDecidePlayerTurn() {
    assertNull(this.gameInitial.getTurn());
    assertEquals(Player.PLAYER_NORTH, this.gameNorthMovedFirst.getTurn());
    assertEquals(Player.PLAYER_NORTH, this.gameSouthMovedFirst.getTurn());
  }

  @Test
  void testDetermineWinner() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final Method determineWinner =
        openMethodForTest(service.getClass().getDeclaredMethod("determineWinner", Game.class));
    determineWinner.invoke(this.service, this.gameInitial);
    determineWinner.invoke(this.service, this.gameFinishedWinnerSouth);
    determineWinner.invoke(this.service, this.gameFinishedWinnerNorth);

    assertNull(this.gameInitial.getWinner());
    assertEquals(Player.PLAYER_SOUTH, this.gameFinishedWinnerSouth.getWinner());
    assertEquals(Player.PLAYER_NORTH, this.gameFinishedWinnerNorth.getWinner());
  }

  @Test
  void testCheckGameOver() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final Method checkGameOver = openMethodForTest(service.getClass().getDeclaredMethod("checkGameOver", Game.class));
    checkGameOver.invoke(this.service, this.gameInitial);
    checkGameOver.invoke(this.service, this.gameFinishedWinnerSouth);
    checkGameOver.invoke(this.service, this.gameFinishedWinnerNorth);

    assertEquals(36, this.gameInitial.getBoard().getStoneCount(Player.PLAYER_NORTH, true));
    assertEquals(36, this.gameInitial.getBoard().getStoneCount(Player.PLAYER_SOUTH, true));
    assertEquals(10, this.gameFinishedWinnerSouth.getBoard().getStoneCount(Player.PLAYER_NORTH, true));
    assertEquals(62,

        this.gameFinishedWinnerSouth.getBoard().getStoneCount(Player.PLAYER_SOUTH, true));
    assertEquals(40, this.gameFinishedWinnerNorth.getBoard().getStoneCount(Player.PLAYER_NORTH, true));
    assertEquals(32, this.gameFinishedWinnerNorth.getBoard().getStoneCount(Player.PLAYER_SOUTH, true));
  }

  @Test
  void testLastEmptyPit() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final Method resetBoard = openMethodForTest(service.getClass().getDeclaredMethod("resetBoard", Game.class));
    final Method lastEmptyPit =
        openMethodForTest(service.getClass().getDeclaredMethod("lastEmptyPit", Game.class, int.class));

    resetBoard.invoke(this.service, this.gameTurnNorth);
    this.gameTurnNorth.getBoard().getPit(Player.PLAYER_NORTH.getPosition()).setStoneCount(0);
    this.gameTurnNorth.getBoard().getPit(Player.PLAYER_SOUTH.getPosition()).setStoneCount(0);
    this.gameTurnNorth.getBoard().getPit(4).setStoneCount(1);
    this.gameTurnNorth.getBoard().getPit(10).setStoneCount(6);

    lastEmptyPit.invoke(this.service, this.gameTurnNorth, 4);

    assertEquals(7, this.gameTurnNorth.getBoard().getStoneCount(Player.PLAYER_NORTH, true));
    assertEquals(0, this.gameTurnNorth.getBoard().getStoneCount(Player.PLAYER_SOUTH, true));
  }

  @Test
  void testValidMovePlayerNorth() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final Method validateMove =
        openMethodForTest(service.getClass().getDeclaredMethod("validateMove", Game.class, int.class));
    validateMove.invoke(this.service, this.gameInitial, 1);
    assertEquals(Player.PLAYER_NORTH, this.gameInitial.getTurn());
  }

  @Test
  void testValidMovePlayerSouth() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    final Method validateMove =
        openMethodForTest(service.getClass().getDeclaredMethod("validateMove", Game.class, int.class));
    validateMove.invoke(this.service, this.gameInitial, 13);
    assertEquals(Player.PLAYER_SOUTH, this.gameInitial.getTurn());
  }

  private Method openMethodForTest(final Method method) {
    ReflectionUtils.makeAccessible(method);
    return method;
  }
}
