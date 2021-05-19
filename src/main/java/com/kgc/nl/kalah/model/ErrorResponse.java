/**
 * 
 */
package com.kgc.nl.kalah.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Randhir Kumar
 * 
 *
 */
@Data
@Schema(name = "ErrorResponse", description = "Contains the Error response.")
public class ErrorResponse {

  private String gameId;
  private String errorDescription;
}
