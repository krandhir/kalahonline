/**
 * 
 */
package com.kgc.nl.kalah.model;

import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Randhir Kumar
 *
 */
@Schema(name = "KalahResponse", description = "Kalah Response wrapper")
public class KalahResponse {

  @Schema(description = "unique identifier of a game")
  private final String id;

  @Schema(description = "link to the game created")
  private final String uri;

  @Schema(description = "json object key-value, where key is the pitId and value is the number of stones in the pit")
  private final Map<Integer, String> status;

  /**
   * Parametrized Constructor
   * 
   * @param id
   * @param uri
   */
  public KalahResponse(final String id, final String uri) {
    this(id, uri, null);
  }

  /**
   * Parametrized constructor
   * 
   * @param id
   * @param uri
   * @param status
   */
  public KalahResponse(final String id, final String uri, final Map<Integer, String> status) {
    this.id = id;
    this.uri = uri;
    this.status = status;
  }

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @return uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * @return status
   */
  public Map<Integer, String> getStatus() {
    return status;
  }

}
