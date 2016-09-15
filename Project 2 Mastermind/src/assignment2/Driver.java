package assignment2;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		/* Create scanner for player input */
		Scanner scan = new Scanner(System.in);
		
		while (true) { 
			
			/* Run in test mode if argument is 1 */
			if (args.length > 0 && args[0].compareTo("1") == 0) {
				Game.runGame(true, scan);
			}
			
			/* Else run normally */
			else {Game.runGame(false, scan);}
		}
	}

}
