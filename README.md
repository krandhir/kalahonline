[![Build Status](https://api.travis-ci.com/krandhir/kalahonline.svg?branch=main)](https://travis-ci.com/krandhir/kalahonline)
[![codecov](https://codecov.io/gh/krandhir/kalahonline/branch/main/graph/badge.svg?token=MbDdfa21zs)](https://codecov.io/gh/krandhir/kalahonline)

## Game of six stone kalah

#### The Goal
Create a *Java RESTful Web Service* that runs a game of 6-stone Kalah. The general rules
of the game are explained on Wikipedia: https://en.wikipedia.org/wiki/Kalah and also below in this document.
This web service will enable to let 2 human players to play the game, each in his own computer. There is no AI
required.

#### Kalah Rules
Each of the two players has **six pits** in front of him/her. To the right of the six pits, each player has a larger pit, his
Kalah or house.
At the start of the game, six stones are put in each pit.
The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in
each of the following pits, including his own Kalah. No stones are put in the opponent's' Kalah. If the players last
stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other
player's turn.
When the last stone lands in an own empty pit, the player captures this stone and all stones in the opposite pit (the
other players' pit) and puts them in his own Kalah.
The game is over as soon as one of the sides run out of stones. The player who still has stones in his/her pits keeps
them and puts them in his/hers Kalah. The winner of the game is the player who has the most stones in his Kalah.

## Getting Started

## Prerequisites

* [Java 1.8 or above ](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - Programming language

### Running

```
./mvnw spring-boot:run
```

### Running the tests

```
./mvnw test
```

## Endpoints
This application provides two endpoints to create a game and to make a move.

API endpoint documentation: <http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#>

## Project creation
Created the project using <https://start.spring.io/>

## Built With

* Java - Programming language
* [Spring Boot](https://projects.spring.io/spring-boot/) - Framework
* [Maven](https://maven.apache.org) - To manage the dependencies
* [JUnit5](https://junit.org/junit5/) - Unit test framework
* [Swagger](https://swagger.io) - Used to generate API docs & UI

Note: KGC - Kalah Game company

## Author

* Randhir kumar
