# EE 422C - Project4 Critters

This project simulates the life of various critters in Java.

##Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

###Prerequisites

[Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

###Installing

####Linux

1. Navigate to where you want the project
2. Clone repository: `git clone https://github.com/atchisonbrent/EE422C-Project4.git`
3. Move to source folder: `cd EE422C-Project4/src/`
4. Compile: `javac assignment4/*.java`
5. Run: `java assignment4.Main`

##Class List

* Main.java
  * Fields:
    * kb : Scanner
    * inputFile : String
    * testOutputString : ByteArrayOutputStream
    * myPackage : String
    * old : PrintStream
  * Methods:
    * main(String[]) : void
* Input.java
  * Fields:
    * myPackage : String
  * Methods:
    * takeInput(Scanner) : void
    * printError(String[]) : void
    * printInvalid(String[]) : void
* Critter.java
  * Fields:
    * myPackage : String
    * population : List<Critter>
    * babies : List<Critter>
    * rand : Random
    * energy : int
    * x_coord : int
    * y_coord : int
    * hasMoved : boolean
    * fighting : boolean
  * Methods:
    * getRandomInt(int) : int
    * setSeed(long) : void
    * toString() : String
    * getEnergy() : int
    * walk(int) : void
    * run(int) : void
    * move(int, int) : void
    * reproduce(Critter, int) : void
    * doTimeStep() : void
    * fight(String) : boolean
    * makeCritter(String) : void
    * getInstances(String) : List<Critter>
    * runStats(List<Critter>) : void
    * clearWorld() : void
    * worldTimeStep() : void
    * displayWorld() : void
    * printEdge() : void
    * printMiddle() : void
* Critter1.java
  * Fields:
    * dir : int
  * Methods:
    * Critter1()
    * toString() : String
    * fight(String) : boolean
    * doTimeStep() : void
* Critter2.java
  * Fields:
    * dir : int
  * Methods:
    * Critter2()
    * toString() : String
    * fight(String) : boolean
    * doTimeStep() : void
* Critter3.java
  * Fields:
    * dir : int
  * Methods:
    * Critter3()
    * toString() : String
    * fight(String) : boolean
    * doTimeStep() : void
* Critter4.java
  * Fields:
    * dir : int
  * Methods:
    * Critter4()
    * toString() : String
    * fight(String) : boolean
    * doTimeStep() : void
* Craig.java
  * Fields:
    * GENE_TOTAL : int
    * genes : int[]
    * dir : int
  * Methods:
    * toString() : String
    * Craig()
    * fight(String) : boolean
    * doTimeStep() : void
    * runStats(List<Critter>) : void
* Algae.java
  * Methods:
    * toString() : String
    * fight(String) : boolean
    * doTimeStep : void
* Params.java
  * Fields:
    * world_width : int
    * world_height : int
    * walk_energy_cost : int
    * run_energy_cost : int
    * rest_energy_cost : int
    * min_reproduce_energy : int
    * refresh_algae_count : int
    * photosynthesis_energy_amount : int
    * start_energy : int
* InvalidCritterException.java
  * Fields:
    * offending_class : String
  * Methods:
    * InvalidCritterException(String)
    * toString() : String

##Authors

* Brent Atchison
* Dhruv Mathew
