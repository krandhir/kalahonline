package com.kgc.nl.kalah.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PitTest {

  @Test
  void testDistributable() {
    final Pit givenPit1 = new Pit(1);
    final Pit givenPit2 = new Pit(14);
    final Pit givenPit3 = new Pit(7);

    assertTrue(givenPit1.isDistributable(Player.PLAYER_NORTH));
    assertTrue(givenPit2.isDistributable(Player.PLAYER_SOUTH));
    assertFalse(givenPit2.isDistributable(Player.PLAYER_NORTH));
    assertFalse(givenPit3.isDistributable(Player.PLAYER_SOUTH));
  }

  @Test
  void testHouse() {
    final Pit givenPit1 = new Pit(7);
    final Pit givenPit2 = new Pit(14);
    final Pit givenPit3 = new Pit(3);
    final Pit givenPit4 = new Pit(9);

    assertTrue(givenPit1.isHouse());
    assertTrue(givenPit2.isHouse());
    assertFalse(givenPit3.isHouse());
    assertFalse(givenPit4.isHouse());
  }

  @Test
  void testInitialization() {
    final Pit givenPit1 = new Pit(1);
    final Pit givenPit2 = new Pit(14);
    final Pit givenPit3 = new Pit(7);

    assertEquals(1, givenPit1.getId());
    assertEquals(14, givenPit2.getId());
    assertEquals(7, givenPit3.getId());
  }

  @Test
  void testInitialStoneCount() {
    final Pit givenPit1 = new Pit(1);
    final Pit givenPit2 = new Pit(7);

    assertEquals(6, givenPit1.getStoneCount());
    assertEquals(0, givenPit2.getStoneCount());
  }

  @Test
  void testOwner() {
    final Pit givenPit1 = new Pit(4);
    final Pit givenPit2 = new Pit(7);
    final Pit givenPit3 = new Pit(10);
    final Pit givenPit4 = new Pit(14);

    assertEquals(Player.PLAYER_NORTH, givenPit1.getOwner());
    assertEquals(Player.PLAYER_NORTH, givenPit2.getOwner());
    assertEquals(Player.PLAYER_SOUTH, givenPit3.getOwner());
    assertEquals(Player.PLAYER_SOUTH, givenPit4.getOwner());
  }

  @Test
  void testStoneCountSet() {
    final Pit givenPit = new Pit(1);
    givenPit.setStoneCount(7);

    assertEquals(7, givenPit.getStoneCount());
  }
}
