/**
 * 
 */
package com.kgc.nl.kalah.model;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Randhir Kumar
 *
 */
@Data
public class Game {

  @Schema(description = "unique identifier of a game")
  private final String id;
  private final KalahBoard board;
  private Player winner;
  private Player turn;

  /**
   * 
   */
  public Game() {
    this.id = UUID.randomUUID().toString();
    this.board = new KalahBoard();
  }

}
