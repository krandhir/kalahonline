package com.kgc.nl.kalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of application, it boots the application. Used to bootstrap and launch a Spring
 * application from a Java main method
 * 
 * @author Randhir kumar
 * 
 */
@SpringBootApplication
public class KalahApplication {

  /**
   * main method
   * 
   * @param args - array of args
   */
  public static void main(String[] args) {
    SpringApplication.run(KalahApplication.class, args);
  }

}
