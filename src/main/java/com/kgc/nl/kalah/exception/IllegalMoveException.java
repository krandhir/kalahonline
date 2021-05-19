/**
 * 
 */
package com.kgc.nl.kalah.exception;

/**
 * @author Randhir Kumar
 *
 */
public class IllegalMoveException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IllegalMoveException(final String message) {
    super("Illegal move: " + message);
  }
}
