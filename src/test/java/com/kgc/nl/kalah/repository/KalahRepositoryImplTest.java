package com.kgc.nl.kalah.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.kgc.nl.kalah.model.Game;
import com.kgc.nl.kalah.repo.IKalahRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class KalahRepositoryImplTest {

  @Autowired
  private IKalahRepository repository;

  @Test
  @DirtiesContext
  void testFind() {
    final Game given = this.repository.save(new Game());
    final Game when = this.repository.find(given.getId());

    assertNotNull(given);
    assertEquals(given, when);
  }

  @Test
  @DirtiesContext
  void testSave() {
    final Game game = this.repository.save(new Game());

    assertNotNull(game);
  }
}
