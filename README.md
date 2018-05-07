# Bootcamp Isolation Game
This is a simple game of isolation utilized to provide hands on experience with architecture and design patterns. The game was designed for 45 minute instructional sessions, thus some patterns have been simplified and others are missing entirely for brevity. This game is also intended for unit and integration test instructional material, intentional bugs are present and no tests are included so that they may be implemented as part of the instruction session.

Note, the completed application is contained with in branch "completed".

## Run Using IntelliJ
* Checkout the application
* Import into Intellij as Gradle project
* Right click on IsolationGameApplication
* Click on 'Run IsoliationGameApplication.main()'

## Software Design Tasks
Most tasks are presented in BDD style format and are meant to familiarise individuals with working with an existing architecture and design patterns.
### 1. Implement Restart Game UI Feature
* Given I am a human player (player 0)
* When I click the "Restart Game" button
* Then the game board should be reconfigured for a new game.

### 2. Implement Player Turn Notification UI Feature
* Given I am any player
* When it is the player's turn
* Text should be displayed stating that it is the player's turn

### 3. Implement Smarter AI Feature
The default AI simply uses random choices to play. Implement a smarter AI.

#### Non-functional requirements:
* AI must generate a new move within 15 seconds on most modern hardware.
* The new AI must be able to win against the random AI at least 8 times out of 10.

### 4. Fix UI Freezing Defect
This is a defect tasks, enhance the game so that the UI does not freeze when the AI Player is taking it's turn.

## Testing Tasks
These testing tasks should use Jacoco to determine test coverage. Refactor wherever necessary for testing.
### 1. Implement 85% Unit Test Coverage

### 2. Implement 85% Integration Test Coverage