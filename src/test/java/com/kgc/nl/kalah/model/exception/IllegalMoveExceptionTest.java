package com.kgc.nl.kalah.model.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.kgc.nl.kalah.exception.IllegalMoveException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class IllegalMoveExceptionTest {

  @Test
  void testInitialization() {
    final IllegalMoveException givenIllegalMoveException = new IllegalMoveException("move1");

    assertEquals("Illegal move: move1", givenIllegalMoveException.getMessage());
  }
}
