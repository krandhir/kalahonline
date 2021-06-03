/**
 * 
 */
package com.kgc.nl.kalah.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kgc.nl.kalah.exception.IllegalMoveException;
import com.kgc.nl.kalah.model.Game;
import com.kgc.nl.kalah.model.KalahBoard;
import com.kgc.nl.kalah.model.Pit;
import com.kgc.nl.kalah.model.Player;
import com.kgc.nl.kalah.repo.IKalahRepository;
import com.kgc.nl.kalah.service.IKalahService;

/**
 * @author Randhir Kumar
 *
 */
@Service
public class KalahServiceImpl implements IKalahService {

  @Autowired
  private IKalahRepository repository;

  /**
   * {@inheritDoc}}
   * 
   */
  @Override
  public Game createGame() {
    return this.repository.save(new Game());
  }

  /**
   * {@inheritDoc}}
   * 
   */
  @Override
  public Game play(final String gameId, final Integer pitId) {
    // retrieve the game from the repository
    final Game game = this.repository.find(gameId);
    distributeStones(game, pitId);
    checkGameOver(game);
    return game;
  }

  private void resetBoard(final Game game) {
    game.getBoard().getPits().parallelStream().filter(
        pit -> (Player.PLAYER_NORTH.getPosition() != pit.getId()) && (Player.PLAYER_SOUTH.getPosition() != pit.getId()))
        .forEach(pit -> pit.setStoneCount(0));
  }

  /**
   * validate the move then distribute the stones and decide the player turn
   * 
   * @param game
   * @param pitId
   */
  private void distributeStones(final Game game, int pitId) {
    final Pit startPit = game.getBoard().getPit(pitId);
    validateMove(game, pitId);
    int stoneToDistribute = startPit.getStoneCount();
    // set the stone count to zero after the first move
    startPit.setStoneCount(0);
    while (stoneToDistribute > 0) {
      // (++pitId) pre-increment to get the immediate pit after the start pit for the stone distribution
      final Pit currentPit = game.getBoard().getPit(++pitId);
      if (currentPit.isDistributable(game.getTurn())) {
        // increase the stone count by 1
        currentPit.setStoneCount(currentPit.getStoneCount() + 1);
        stoneToDistribute--;
      }
    }
    // check if the last pit is empty
    lastEmptyPit(game, pitId);
    // decide if the player will get another turn else next player will get the turn
    decidePlayerTurn(game, pitId);
  }

  /**
   * Check if the game is over by counting the stones and then reset the board
   * 
   * @param game
   */
  private void checkGameOver(final Game game) {
    final int playerNorthPitStoneCount = game.getBoard().getStoneCount(Player.PLAYER_NORTH, false);
    final int playerSouthPitStoneCount = game.getBoard().getStoneCount(Player.PLAYER_SOUTH, false);
    if ((playerNorthPitStoneCount == 0) || (playerSouthPitStoneCount == 0)) {
      final Pit houseNorth = game.getBoard().getPit(Player.PLAYER_NORTH.getPosition());
      final Pit houseSouth = game.getBoard().getPit(Player.PLAYER_SOUTH.getPosition());
      houseNorth.setStoneCount(houseNorth.getStoneCount() + playerNorthPitStoneCount);
      houseSouth.setStoneCount(houseSouth.getStoneCount() + playerSouthPitStoneCount);
      
      determineWinner(game);
      // reset the board
      resetBoard(game);
    }
  }

  /**
   * Determine the game winner
   * 
   * @param game
   */
  private void determineWinner(final Game game) {
    final int houseNorthStoneCount = game.getBoard().getStoneCount(Player.PLAYER_NORTH, true);
    final int houseSouthStoneCount = game.getBoard().getStoneCount(Player.PLAYER_SOUTH, true);
    if (houseNorthStoneCount > houseSouthStoneCount) {
      game.setWinner(Player.PLAYER_NORTH);
    } else if (houseNorthStoneCount < houseSouthStoneCount) {
      game.setWinner(Player.PLAYER_SOUTH);
    }
  }

  /**
   * Checks if the last pit in the turn is empty and belongs to the current player also it's not the
   * Kalah house then the player captures this stone and all the stones from the opposite pit
   * 
   * @param game
   * @param endPitId
   */
  private void lastEmptyPit(final Game game, final int endPitId) {
    final Pit endPit = game.getBoard().getPit(endPitId);
    if (!endPit.isHouse() && endPit.getOwner().equals(game.getTurn()) && (endPit.getStoneCount() == 1)) {
      final Pit oppositePit = game.getBoard().getPit(KalahBoard.PIT_END_INDEX - endPit.getId());
      if (oppositePit.getStoneCount() > 0) {
        final Pit house = game.getBoard().getPit(endPit.getOwner().getPosition());
        house.setStoneCount((house.getStoneCount() + oppositePit.getStoneCount()) + endPit.getStoneCount());
        oppositePit.setStoneCount(0);
        endPit.setStoneCount(0);
      }
    }
  }

  /**
   * Check if the last pit is the players own Kalah. If true then the player will get another turn
   * 
   * @param game
   * @param pitId
   */
  private void decidePlayerTurn(final Game game, final int pitId) {
    final Pit pit = game.getBoard().getPit(pitId);
    // if the stone lands in own Kalah then the player will get another turn
    if (pit.isHouse() && Player.PLAYER_NORTH.equals(pit.getOwner()) && Player.PLAYER_NORTH.equals(game.getTurn())) {
      game.setTurn(Player.PLAYER_NORTH);
    } else if (pit.isHouse() && Player.PLAYER_SOUTH.equals(pit.getOwner())
        && Player.PLAYER_SOUTH.equals(game.getTurn())) {
      game.setTurn(Player.PLAYER_SOUTH);
    } else {
      // not own Kalah, another players turn
      if (Player.PLAYER_NORTH.equals(game.getTurn())) {
        game.setTurn(Player.PLAYER_SOUTH);
      } else {
        game.setTurn(Player.PLAYER_NORTH);
      }
    }
  }

  /**
   * This method validates if the move is valid else exception is thrown. And in case if player turn
   * is not yet decided then sets the turn to pit owner
   * 
   * @param game
   * @param startPitId
   */
  private void validateMove(final Game game, final int startPitId) {
    final Pit startPit = game.getBoard().getPit(startPitId);

    if (startPit.isHouse()) {
      throw new IllegalMoveException("Can not start from house");
    }
    if (Player.PLAYER_NORTH.equals(game.getTurn()) && !Player.PLAYER_NORTH.equals(startPit.getOwner())) {
      throw new IllegalMoveException("It's Player North's turn");
    }
    if (Player.PLAYER_SOUTH.equals(game.getTurn()) && !Player.PLAYER_SOUTH.equals(startPit.getOwner())) {
      throw new IllegalMoveException("It's Player South's turn");
    }
    if (startPit.getStoneCount() == 0) {
      throw new IllegalMoveException("Can not start from empty pit");
    }
    // turn not yet decided
    if (game.getTurn() == null) {
      if (Player.PLAYER_NORTH.equals(startPit.getOwner())) {
        game.setTurn(Player.PLAYER_NORTH);
      } else {
        game.setTurn(Player.PLAYER_SOUTH);
      }
    }
  }

}
