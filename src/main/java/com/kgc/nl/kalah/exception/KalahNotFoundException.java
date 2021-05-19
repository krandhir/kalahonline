/**
 * 
 */
package com.kgc.nl.kalah.exception;

/**
 * @author Randhir Kumar
 *
 */
public class KalahNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public KalahNotFoundException(final String id) {
    super("Could not find game " + id);
  }

}
