package com.kgc.nl.kalah.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Health Checker. Checks the health of the application and returns fixed text which can be used to
 * check the running status of the application
 */
@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

  /**
   * Check health of the application
   * 
   * @return response string
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = "The health of the application.")
  @ApiResponse(responseCode = "200", description = "Healthy")
  public String healthCheck() {
    return "Healthy";
  }
}
