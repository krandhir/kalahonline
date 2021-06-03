/**
 * 
 */
package com.kgc.nl.kalah.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.kgc.nl.kalah.model.KalahResponse;
import com.kgc.nl.kalah.util.UUID;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Interface for controller which provides methods to handle web requests.
 * 
 * @author Randhir kumar
 *
 */
@Validated
public interface IKalahController {

  /**
   * Creates a Kalah Game
   * 
   * @return ResponseEntity<KalahResponse>
   */
  @Operation(summary = "Create a Game",
      description = "This endpoint creates a game of Kalah and the response contains the game Id and URL of the game")
  @PostMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<KalahResponse> createGame();

  /**
   * Make a move
   * 
   * @param gameId
   * @param pitId
   * @return ResponseEntity<KalahResponse>
   */
  @Operation(summary = "Make a move", description = "Call this endpoint to make a move using the gameId and pitId")
  @PutMapping(value = "/games/{gameId}/pits/{pitId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<KalahResponse> playGame(
      @PathVariable("gameId") final @UUID(message = "Invalid game Id") String gameId,
      @PathVariable("pitId") final @Min(value = 1) @Max(value = 14) Integer pitId);

}
