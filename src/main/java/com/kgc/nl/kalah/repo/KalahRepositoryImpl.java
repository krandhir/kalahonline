/**
 * 
 */
package com.kgc.nl.kalah.repo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.kgc.nl.kalah.exception.KalahNotFoundException;
import com.kgc.nl.kalah.model.Game;

/**
 * This class provides the persistence logic. Storing and retrieving the values from a Map<String,
 * Game>.
 *
 */
@Service
public class KalahRepositoryImpl implements IKalahRepository {

  private final Map<String, Game> repositoryMock = new HashMap<>();

  /**
   * {@inheritDoc}}
   * 
   */
  @Override
  public Game find(final String id) {
    final Game game = this.repositoryMock.get(id);
    if (game == null) {
      throw new KalahNotFoundException(id);
    }
    return game;
  }

  /**
   * {@inheritDoc}}
   * 
   */
  @Override
  public Game save(final Game game) {
    this.repositoryMock.put(game.getId(), game);
    return this.find(game.getId());
  }

}
