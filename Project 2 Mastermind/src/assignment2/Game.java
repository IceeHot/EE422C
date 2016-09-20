/* 
 * Game.java
 * EE422C Project 2 (Mastermind)
 * Brent Atchison
 * bma862
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

import java.util.Scanner;

public class Game{
	
	private static boolean testMode;
	private static Game newGame;
	
	/**
	 * Game object
	 * @param mode sets testMode
	 */
	Game(boolean mode) {
		testMode = mode;
	}
	
	/**
	 * Loop that runs the game
	 */
	//@SuppressWarnings("static-access")
	public static void runGame(boolean testMode, Scanner scan) {
		
		/* Initialize new game */		
		setNewGame(new Game(testMode));
		
		/* Reset variable */
		GameFunctions.remGuesses = GameConfiguration.guessNumber;
		
		/* Populate color letters */
		for (int i = 0; i < GameConfiguration.colors.length; i++) {
			GameFunctions.colorLetters[i] = GameConfiguration.colors[i].charAt(0);
		}
		
		/* Generate code */
		Print.printGenerate();
		String strCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		
		/* Print code if testMode is on */
		if (Game.testMode) {
			Print.printCode(strCode);
		}
		
		
		/* Convert code to character array */
		char[] charCode = strCode.toCharArray();
		
		/* Continue while player still has remaining guesses */
		while (GameFunctions.remGuesses > 0) {
			
			/* Print turn information */
			Print.printRemGuesses();
			Print.printNextTurn();
			
			/* Input guess */
			char[] guess = Input.inputGuess(scan, charCode);
			
			/* Generate result */
			if (GameFunctions.generateResult(guess, charCode, false)) {
				Print.win();
				Print.anotherGame();
				Input.ready(scan);
				break;
			}
			
			/* Subtract from turns remaining */
			GameFunctions.remGuesses--;
		}
		
		/* Out of turns, player lost */
		if (GameFunctions.remGuesses <= 0) {
			Print.lose();
			Print.anotherGame();
			Input.ready(scan);
		}
	}

	public static Game getNewGame() {
		return newGame;
	}

	public static void setNewGame(Game newGame) {
		Game.newGame = newGame;
	}

}
