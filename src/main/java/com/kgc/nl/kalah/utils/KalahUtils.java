/**
 * 
 */
package com.kgc.nl.kalah.utils;

import java.util.regex.Pattern;

/**
 * @author
 *
 */
public class KalahUtils {

  /**
   * Private constructor
   */
  private KalahUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * This method validates the kalah ID against the regex.
   * 
   * @param value String value to validate
   * @return true if the value matches the pattern else false
   */
  public static boolean isValidKid(String value) {
    String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    return Pattern.matches(regex, value);
  }

}
