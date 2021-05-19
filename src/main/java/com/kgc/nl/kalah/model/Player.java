/**
 * 
 */
package com.kgc.nl.kalah.model;


/**
 * @author Randhir Kumar
 *
 */
public enum Player {
  PLAYER_NORTH(KalahBoard.PIT_END_INDEX / 2), PLAYER_SOUTH(KalahBoard.PIT_END_INDEX);

  private int position;

  /**
   * @param position
   */
  Player(final int position) {
    this.position = position;
  }

  /**
   * @return position
   */
  public int getPosition() {
    return this.position;
  }
}
