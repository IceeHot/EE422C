/* 
 * Input.java
 * EE422C Project 2 (Mastermind)
 * Brent Atchison
 * bma862
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

import java.util.Scanner;

public class Input {
	
	/**
	 * Asks player if ready
	 * @param scan takes player input
	 */
	public static void ready(Scanner scan) {	
		
		while (true) {
			
			/* Scan for player input */
			String text = scan.nextLine();
			text = text.toUpperCase();
			
			/* Player is ready */
			if (text.equals("Y")) { return;	}
			
			/* Player is not ready */
			else if (text.equals("N")) {
				scan.close();
				System.exit(0);
			}
			
			/* Invalid input */
			else {
				Print.printInvalidInput();
				Print.printReady();
			}
		}
	}
	
	/**
	 * Input guess from player
	 * @param scan takes player input
	 * @param charCode is game key
	 * @return character array with player guess
	 */
	public static char[] inputGuess(Scanner scan, char[] charCode) {
		
		while (true) {
			
			/* Scan for player input */
			String guess = scan.nextLine();
			
			/* Player is asking for history */
			if (guess.equals("HISTORY")) {
				System.out.println();
				GameFunctions.getHistory(charCode);
				System.out.println();
				Print.printNextTurn();
			}
			
			else {
				
				/* Print guess to console */
				System.out.print(guess + " -> ");
	
				/* Convert to character array */
				char[] textArray = guess.toCharArray();
				
				/* Incorrect input */
				if ((guess.length() != GameConfiguration.pegNumber) || !searchColors(textArray)) {
					Print.printInvalidGuess();
					Print.printNextTurn();
				}
				
				/* Correct input */
				else {
					
					/* Store guess in previous guesses array */
					for (int i = 0; i < GameConfiguration.pegNumber; i++) {
						GameFunctions.prevGuesses[GameConfiguration.guessNumber - GameFunctions.remGuesses][i] = textArray[i];
					}
					
					/* Print guess */
					System.out.print("Result:  ");
					
					/* Return guess */
					return textArray;
				}
			}
		}
	}
	
	/**
	 * Search colorLetters array for proper input
	 * @return true if all letters found
	 * false if any letters not found
	 */
	public static boolean searchColors(char[] textArray) {
		
		/* Number of letters found */
		int count = 0;
		
		/* Compare guess with possible letters */
		for (int i = 0; i < textArray.length; i++) {
			for (int j = 0; j < GameFunctions.colorLetters.length; j++) {
				if (textArray[i] == GameFunctions.colorLetters[j]) {
					count++;
				}
			}
		}
		
		/* Return true if all letters in guess are found */
		return count == textArray.length;
	}
}
