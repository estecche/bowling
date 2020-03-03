# Bowling challenge project

This is a very simple Java project to show a Bowling score table.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Minimum Java 8
* Gradle 5.2 - you can download it from here: https://gradle.org/next-steps/?version=5.2&format=all
* The program assumes that at least there are two players.

### Building the project

```
./gradlew clean build javadoc
```

### Running the project
Inside the project, you can execute the following:

```
java -jar build/libs/bowling-1.0.jar ./src/test/resources/scores3.txt
```