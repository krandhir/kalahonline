/**
 * 
 */
package com.kgc.nl.kalah.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Randhir Kumar
 *
 */
public class Pit {

  @Schema(
      description = "id of the pit selected to make a move. Pits are numbered from 1 to 14 where 7 and 14 are the kalah (or house) of each player")
  private final int id;
  private int stoneCount;

  @JsonCreator
  public Pit(@JsonProperty("id") final int id) {
    this.id = id;
    if (!this.isHouse()) {
      this.setStoneCount(6);
    }
  }

  public int getId() {
    return this.id;
  }

  public Player getOwner() {
    if (this.getId() <= Player.PLAYER_NORTH.getPosition()) {
      return Player.PLAYER_NORTH;
    } else {
      return Player.PLAYER_SOUTH;
    }
  }

  public int getStoneCount() {
    return this.stoneCount;
  }

  public void setStoneCount(final int stoneCount) {
    this.stoneCount = stoneCount;
  }

  public boolean isDistributable(final Player turn) {
    return (!turn.equals(Player.PLAYER_NORTH) || (this.getId() != Player.PLAYER_SOUTH.getPosition()))
        && (!turn.equals(Player.PLAYER_SOUTH) || (this.getId() != Player.PLAYER_NORTH.getPosition()));
  }

  public boolean isHouse() {
    return (this.getId() == Player.PLAYER_NORTH.getPosition()) || (this.getId() == Player.PLAYER_SOUTH.getPosition());
  }

}
