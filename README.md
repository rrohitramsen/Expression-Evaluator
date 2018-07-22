GAME of Life
==========================

`Rules of the Game`
----------------------------------
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead, or "populated" or "unpopulated". Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
1.	Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
2.	Any live cell with two or three live neighbours lives on to the next generation.
3.	Any live cell with more than three live neighbours dies, as if by overpopulation.
4.	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.


## Sample seeds file

 * [Beacon Seed](/src/test/resources/beacon_seed.txt)
 * [Blinker Seed](/src/test/resources/blinker_seed.txt)
 * [Glider Seed](/src/test/resources/glider_seed.txt)
 * [Toad Seed](/src/test/resources/toad_seed.txt)
 * [Tub Seed](/src/test/resources/tub_seed.txt)

# `Junit Test execute for all the sample seeds`.
~~~
com.test.gameoflife.config : Game of life start.

com.test.gameoflife.game : player and universe class.

com.test.gameoflife.helper: SeedFileReader class..
~~~

## `How to Start`
* [game_of_life](/game_of_life.sh) script that automates the entire build and execute process.

```
$ ./game_of_life.sh
```

* It will build the project and create the executable jar.
* In default it will execute the game of life for the below mentioned sample seeds.
* User can also input the seed file name.
~~~
$ cd gameoflife
$ ./game_of_life.sh
~~~

This is a maven project, can run using below mentioned commands,
~~~
$ mvn test - To execute the Junit tests.
~~~

~~~
$ mvn package - To generate the executable jar --> game_of_life-1.0-SNAPSHOT.jar

$ java -jar target/game_of_life-1.0-SNAPSHOT.jar - To execute the application.
~~~

## `How to run File`
~~~
$ java -jar target/game_of_life-1.0-SNAPSHOT.jar beacon_seed.txt
$ java -jar target/game_of_life-1.0-SNAPSHOT.jar ../src/test/resources/<sample_seeds>
~~~



