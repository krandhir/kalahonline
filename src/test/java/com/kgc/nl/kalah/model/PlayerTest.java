package com.kgc.nl.kalah.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlayerTest {

  @Test
  void testHouseIndex() {
    final Player givenPlayer1 = Player.PLAYER_NORTH;
    final Player givenPlayer2 = Player.PLAYER_SOUTH;

    assertEquals(7, givenPlayer1.getPosition());
    assertEquals(14, givenPlayer2.getPosition());
  }
}
