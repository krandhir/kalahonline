package com.kgc.nl.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class KalahExceptionHandler {

  @ResponseBody
  @ExceptionHandler(KalahNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String gameNotFoundHandler(final KalahNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(IllegalMoveException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String illegalMoveHandler(final IllegalMoveException ex) {
    return ex.getMessage();
  }
}
