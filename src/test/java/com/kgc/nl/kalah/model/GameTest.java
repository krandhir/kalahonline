package com.kgc.nl.kalah.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameTest {

  @Test
  void testInitialization() {
    final Game givenGame = new Game();

    assertNotNull(givenGame.getBoard());
    assertNull(givenGame.getTurn());
    assertNull(givenGame.getWinner());
  }

  @Test
  void testWinner() {
    final Game givenGame = new Game();
    givenGame.setWinner(Player.PLAYER_NORTH);

    assertEquals(Player.PLAYER_NORTH, givenGame.getWinner());

    givenGame.setWinner(Player.PLAYER_SOUTH);

    assertEquals(Player.PLAYER_SOUTH, givenGame.getWinner());
  }

  @Test
  void testTurn() {
    final Game givenGame = new Game();
    givenGame.setTurn(Player.PLAYER_NORTH);

    assertEquals(Player.PLAYER_NORTH, givenGame.getTurn());

    givenGame.setTurn(Player.PLAYER_SOUTH);

    assertEquals(Player.PLAYER_SOUTH, givenGame.getTurn());
  }
}
