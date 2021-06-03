package com.kgc.nl.kalah.model;

import lombok.Data;

/**
 * DTO for error response
 * 
 * @author Randhir Kumar
 *
 */
@Data
public class ErrorResponse {

  private String message;
  private String errorCode;

}
