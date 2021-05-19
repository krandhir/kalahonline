package com.kgc.nl.kalah.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class KalahBoardTest {

  @Test
  void testGetPit() {
    final KalahBoard givenBoard = new KalahBoard();
    final Pit givenPit = givenBoard.getPit(4);

    assertNotNull(givenPit);
    assertEquals(4, givenPit.getId());
  }

  @Test
  void testInitialization() {
    final KalahBoard givenBoard = new KalahBoard();

    assertNotNull(givenBoard.getPits());
    assertEquals(KalahBoard.PIT_END_INDEX, givenBoard.getPits().size());
  }

  @Test
  void testStoneCount() {
    final KalahBoard givenBoard1 = new KalahBoard();
    final KalahBoard givenBoard2 = new KalahBoard();
    givenBoard2.getPit(5).setStoneCount(0);
    givenBoard2.getPit(11).setStoneCount(9);

    assertEquals(36, givenBoard1.getStoneCount(Player.PLAYER_NORTH, true));
    assertEquals(30, givenBoard2.getStoneCount(Player.PLAYER_NORTH, true));
    assertEquals(39, givenBoard2.getStoneCount(Player.PLAYER_SOUTH, true));
  }
}
