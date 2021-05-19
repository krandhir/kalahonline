package com.kgc.nl.kalah.model.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.kgc.nl.kalah.exception.KalahNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class KalahNotFoundExceptionTest {

  @Test
  void testInitialization() {
    final KalahNotFoundException givenGameNotFoundException = new KalahNotFoundException("game1");

    assertEquals("Could not find game game1", givenGameNotFoundException.getMessage());
  }
}
