/**
 * 
 */
package com.kgc.nl.kalah.service;

import com.kgc.nl.kalah.model.Game;

/**
 * Service interface
 * 
 * @author Randhir Kumar
 *
 */
public interface IKalahService {

  /**
   * Service operation to create a game
   * 
   * @return {@link Game}
   */
  Game createGame();

  /**
   * Service operation to make a move
   * 
   * @param gameId unique identifier of a game
   * @param pitId id of the pit selected to make a move. Pits are numbered from 1 to 14 where 7 and 14
   *        are the kalah (or house) of each player
   * @return {@linkplain Game}
   */
  Game play(String gameId, Integer pitId);
}
