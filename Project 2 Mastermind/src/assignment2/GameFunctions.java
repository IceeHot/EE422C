/* 
 * GameConfig.java
 * EE422C Project 2 (Mastermind)
 * Brent Atchison
 * bma862
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

public class GameFunctions {
	
	/* Remaining guesses */
	public static int remGuesses;
	
	/* Color letters as characters */
	public static final char[] colorLetters = new char[GameConfiguration.colors.length];
	
	/* Guess history in array of character arrays */
	public static char[][] prevGuesses = new char[GameConfiguration.guessNumber][GameConfiguration.pegNumber];
	
	/**
	 * Compares guess and charCode, returns turn information
	 * @param guess is player guess
	 * @param charCode is generated code
	 * @return true if player guessed correctly
	 * false if player did not
	 */
	public static boolean generateResult(char[] guess, char[] charCode, boolean history) {
		
		/* Number of black and white pegs */
		int blackPegs = 0;
		int whitePegs = 0;
		
		/* Clone guess and code */
		char[] tempCode = charCode.clone();
		char[] tempGuess = guess.clone();
		
		/* Count black pegs */
		for (int i = 0; i < guess.length; i++) {
			if (tempGuess[i] == tempCode[i]) {
				blackPegs++;
				tempGuess[i] = 0;
				tempCode[i] = 1;
			}
		}
		
		/* Count white pegs */
		for (int i = 0; i < tempGuess.length; i++) {
			for (int j = 0; j < tempCode.length; j++) {
				if (tempGuess[i] == tempCode[j]) {
					whitePegs++;
					tempGuess[i] = 0;
					tempCode[j] = 1;
					break;
				}
			}
		}
		
		/* Check if game is won */
		if (blackPegs == GameConfiguration.pegNumber) {
			return true;
		}
		
		/* Check if printing history */
		if (history) {
			Print.printHistory(blackPegs, whitePegs);
		}
		
		/* Prints result of guess */
		else {
			Print.printResult(blackPegs, whitePegs);
		}
		
		return false;
	}
	
	/**
	 * Prints history of game
	 * @param charCode is game key
	 */
	public static void getHistory(char[] charCode) {
		for (int i = 0; i < GameConfiguration.guessNumber - remGuesses; i++) {
			System.out.print(String.valueOf(prevGuesses[i]) + "\t\t");
			generateResult(prevGuesses[i], charCode, true);
		}
	}
}
