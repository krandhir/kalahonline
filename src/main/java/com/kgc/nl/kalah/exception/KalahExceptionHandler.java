package com.kgc.nl.kalah.exception;

import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.kgc.nl.kalah.model.ErrorResponse;

@ControllerAdvice
public class KalahExceptionHandler {

  @ResponseBody
  @ExceptionHandler(KalahNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorResponse gameNotFoundHandler(final KalahNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
    return errorResponse;
  }

  @ResponseBody
  @ExceptionHandler(IllegalMoveException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorResponse illegalMoveHandler(final IllegalMoveException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
    return errorResponse;
  }

  @ResponseBody
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorResponse constraintViolationHandler(final ConstraintViolationException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
    return errorResponse;
  }
}
