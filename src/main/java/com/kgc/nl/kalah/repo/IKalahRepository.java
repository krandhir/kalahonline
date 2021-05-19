/**
 * 
 */
package com.kgc.nl.kalah.repo;

import com.kgc.nl.kalah.model.Game;

/**
 * Interface for the repository
 * 
 * @author Randhir Kumar
 *
 */
public interface IKalahRepository {

  /**
   * Retrieves the {@linkplain Game} based on id
   * 
   * @param id
   * @return object of Game
   */
  Game find(final String id);

  /**
   * Stores the {@linkplain Game} details
   * 
   * @param game
   * @return {@link Game}
   */
  Game save(final Game game);

}
