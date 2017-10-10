# Connect4Minimax
Player vs. Minimax AI Connect4 game written in Java.

DESCRIPTION

This project is a human vs. AI Connect4 game. The human is white and goes first, while the AI is black. Each side takes turns selecting their column (from 0 to 6) and either a 4-streak or full board ends the game.

The AI uses a 5-depth minimax algorithm variant. Negamax is used because Connect4 is a zero-sum game. The evaluation function used is quite simple in theory, but lengthy in code. Count all 1-streaks (1pt each), 2-streaks (10pts each), 3-streaks (50pts each), and 4-streaks (500pts each) for the AI, then sum the points to get a score. Do the same for the player and subtract the AI score from the player score. This technique tends to make the AI block the player rather than stretch its own advantage. This was done purposely so that the AI can be studied longer. Unfortunately, this evaluation function isn't very good unless the player is a beginner, but it does make some smart moves.



USAGE:

Please note that this program has only been tested in Terminal for Mac OSX Sierra. To play:

	1) cd to just outside the "connect4" directory
	2) enter "javac connect4/Connect4Main.java" to compile
	3) enter "java connect4.Connect4Main" to run
	4) follow prompts

Usage video: https://youtu.be/qj8bJxlUv9w
