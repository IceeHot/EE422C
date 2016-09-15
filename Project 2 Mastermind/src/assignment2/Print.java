/* 
 * Print.java
 * EE422C Project 2 (Mastermind)
 * Brent Atchison
 * bma862
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

public class Print {
	
	/** Prints welcome message */
	public static void printWelcome() {
		
		System.out.println("\nWelcome to Mastermind. Here are the rules.\n");
		
		System.out.println("This is a text version of the classic board game Mastermind.");
		
		System.out.println("The computer will think of a secret code. The code consists of 4");
		
		System.out.println("colored pegs.");
		
		System.out.println("The pegs MUST be one of six colors: blue, green, orange, purple,");
		
		System.out.println("red, or yellow. A color may appear more than once in the code. You");
		
		System.out.println("try to guess what colored pegs are in the code and what order they");
		
		System.out.println("are in.   After you make a valid guess the result (feedback) will be");
		
		System.out.println("displayed.");
		
		System.out.println("The result consists of a black peg for each peg you have guessed");
		
		System.out.println("exactly correct (color and position) in your guess.  For each peg in");
		
		System.out.println("the guess that is the correct color, but is out of position, you get");
		
		System.out.println("a white peg.  For each peg that is fully incorrect, you get no");
		
		System.out.println("feedback.");
		
		System.out.println("Only the first letter of the color is displayed. B for Blue, R for");
		
		System.out.println("Red, and so forth.");
		
		System.out.println("When entering guesses you only need to enter the first character of");
		
		System.out.println("each color as a capital letter.\n");
		
		System.out.println("You have 12 guesses to figure out the secret code or you lose the");
		
		System.out.print("game.  ");
	}
	
	/** Asks player if ready */
	public static void printReady() {
		System.out.print("Are you ready to play? (Y/N): ");
	}
	
	/** Player entered invalid input */
	public static void printInvalidInput() {
		System.out.println("Invalid input. Please try again.");
	}
	
	/** Prints code generation */
	public static void printGenerate() {
		System.out.print("\nGenerating secret code ....");
	}
	
	public static void printCode(String strCode) {
		System.out.print("(for this example the secret code is\n" + strCode + ")");
	}
	
	/** Prints guesses remaining */
	public static void printRemGuesses() {
		if (GameFunctions.remGuesses != 1) {
			System.out.println("\n\nYou have " + GameFunctions.remGuesses + " guesses left.");
		}
		else {
			System.out.println("\n\nYou have " + GameFunctions.remGuesses + " guess left.");
		}
	}
	
	/** Prints next turn information */
	public static void printNextTurn() {
		System.out.println("What is your next guess?\n"
				+ "Type in the characters for your guess and press enter.");
		System.out.print("Enter guess: ");
	}
	
	/** Player entered invalid guess */
	public static void printInvalidGuess() {
		System.out.println("INVALID GUESS\n");
	}
	
	/**
	 * Prints results based on given pegs
	 * @param blackPegs is number of black pegs
	 * @param whitePegs is number of white pegs
	 */
	public static void printResult(int blackPegs, int whitePegs) {
		
		/* No pegs */
		if (blackPegs == 0 && whitePegs == 0) {
			System.out.print("No pegs");
		}
		
		/* No white pegs */
		else if (blackPegs > 0 && whitePegs == 0) {
			
			/* One black peg */
			if (blackPegs == 1) {
				System.out.print(blackPegs + " black peg");
			}
			
			/* More than one black peg */
			else {
				System.out.print(blackPegs + " black pegs");
			}
		}
		
		/* No black pegs */
		else if (blackPegs == 0 && whitePegs > 0) {
			
			/* One white peg */
			if (whitePegs == 1) {
				System.out.print(whitePegs + " white peg");
			}
			
			/* More than one white peg */
			else {
				System.out.print(whitePegs + " white pegs");
			}
		}
		
		else if (blackPegs > 0 && whitePegs > 0) {
			
			/* One black peg, one white peg */
			if (blackPegs == 1 && whitePegs == 1) {
				System.out.print(blackPegs + " black peg, " + whitePegs + " white peg");
			}
			
			/* More than one of each peg */
			else {
				System.out.print(blackPegs + " black pegs, " + whitePegs + " white pegs");
			}
		}
		return;
	}
	
	/**
	 * Prints results in history format
	 * @param blackPegs is number of black pegs
	 * @param whitePegs is number of white pegs
	 */
	public static void printHistory(int blackPegs, int whitePegs) {
		System.out.println(blackPegs + "B_" + whitePegs + "W");
		return;
		
	}
	
	/** Player won */
	public static void win() {
		System.out.println(GameConfiguration.pegNumber + " black pegs – You win!!\n");
	}
	
	/** Player lost */
	public static void lose() {
		System.out.println("(Sorry, you are out of guesses. You lose, boo-hoo.)\n");
	}
	
	/** Ask for another game */
	public static void anotherGame() {
		System.out.print("Are you ready for another game (Y/N): ");
	}
}
