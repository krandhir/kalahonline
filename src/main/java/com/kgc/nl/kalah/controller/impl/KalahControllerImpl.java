/**
 * 
 */
package com.kgc.nl.kalah.controller.impl;

import java.net.InetAddress;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.kgc.nl.kalah.controller.IKalahController;
import com.kgc.nl.kalah.model.Game;
import com.kgc.nl.kalah.model.KalahResponse;
import com.kgc.nl.kalah.model.Pit;
import com.kgc.nl.kalah.service.IKalahService;

/**
 * RestController Implementation
 * 
 * @author Randhir Kumar
 *
 */
@RestController
public class KalahControllerImpl implements IKalahController {

  private IKalahService service;

  private final Environment hostEnvironment;

  @Autowired
  public KalahControllerImpl(final IKalahService service, final Environment hostEnvironment) {
    this.service = service;
    this.hostEnvironment = hostEnvironment;
  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public ResponseEntity<KalahResponse> playGame(String gameId, Integer pitId) {
    final Game game = this.service.play(gameId, pitId);
    final Map<Integer, String> status = game.getBoard().getPits().stream()
        .collect(Collectors.toMap(Pit::getId, value -> Integer.toString(value.getStoneCount())));
    final KalahResponse gameResponse = new KalahResponse(game.getId(), getHost(game.getId()), status);
    return ResponseEntity.status(HttpStatus.OK).body(gameResponse);

  }

  /**
   * {@inheritDoc}
   * 
   */
  @Override
  public ResponseEntity<KalahResponse> createGame() {
    final Game game = this.service.createGame();
    final KalahResponse gameResponse = new KalahResponse(game.getId(), getHost(game.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(gameResponse);
  }

  /**
   * Construct the url
   * 
   * @param gameId
   * @return hostname
   */
  private String getHost(final String gameId) {
    final int port = hostEnvironment.getProperty("server.port", Integer.class, 8080);
    return String.format("http://%s:%s/games/%s", InetAddress.getLoopbackAddress().getHostName(), port, gameId);
  }

}
