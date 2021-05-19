/**
 * 
 */
package com.kgc.nl.kalah.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Randhir Kumar
 *
 */
public class KalahBoard {
  public static final int PIT_START_INDEX = 1;
  public static final int PIT_END_INDEX = 14;

  private final List<Pit> pits;

  public KalahBoard() {
    this.pits = new ArrayList<>();
    for (int i = KalahBoard.PIT_START_INDEX; i <= KalahBoard.PIT_END_INDEX; i++) {
      this.pits.add(new Pit(i));
    }
  }

  public Pit getPit(final int index) {
    return this.pits.get((index - 1) % KalahBoard.PIT_END_INDEX);
  }

  public List<Pit> getPits() {
    return this.pits;
  }

  public int getStoneCount(final Player player, final boolean includeHouse) {
    return this.getPits().stream().filter(pit -> (pit.getOwner().equals(player) && (includeHouse || !pit.isHouse())))
        .mapToInt(Pit::getStoneCount).sum();
  }

}
