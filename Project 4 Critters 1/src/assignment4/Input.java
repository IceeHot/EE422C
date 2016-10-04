/* 
 * CRITTERS Input.java
 * EE422C Project 4 submission by
 * Brent Atchison
 * bma862
 * 16455
 * Dhruv Mathew
 * dkm989
 * 16455
 * Slip days used: <0>
 * Fall 2016
 */

package assignment4;
import java.util.*;

public class Input {
	
	public static void takeInput(Scanner kb) {
		
		while (true) {
			
			/* Print prompt */
			System.out.print("critters> ");
			
			/* Next line of input to string array */
			String[] input = kb.nextLine().split(" ");
			
			for (int i = 0; i < input.length; i++) {
				
				/* Command is quit */
				if (input[i].equals("quit")) { 
					if (input.length == 1) { System.exit(0); }
					else { printInvalid(input); break; }
				}
				
				/* Command is show */
				else if (input[i].equals("show")) {
					if (input.length == 1) { Critter.displayWorld(); break; }
					else { printInvalid(input); break; }
				}
				
				/* Command is step */
				else if (input[i].equals("step")) {
					
					/* Check for integer after step */
					if (i < input.length - 1) {
						
						/* Number of steps to take */
						int steps;
						
						/* Try parsing an integer */
						try { steps = Integer.parseInt(input[i + 1]); }
						catch (NumberFormatException e) { printError(input); break; }
						
						/* Take steps */
						for (int j = 0; j < steps; j++) { Critter.worldTimeStep(); }
						
					}
					
					/* Take one step */
					else { Critter.worldTimeStep(); }
					
					break;
				}
				
				/* Command is seed */
				else if (input[i].equals("seed")) {
					
					/* Set seed with long input */
					if (i < input.length - 1) {
						try { Critter.setSeed(Long.parseLong(input[i + 1])); }
						catch (NumberFormatException e) { printError(input); break; } 
					}
					
					/* Invalid input */
					else { printError(input); }
					
					break;
				}
				
				/* Command is make */
				else if (input[i].equals("make")) {
					
					/* Check for name after make */
					if (i < input.length - 1) {
						
						/* Number of critters to initialize */
						int count = 1;
						
						/* Check for specified number of critters to initialize */
						if (i < input.length - 2) {
							try { count = Integer.parseInt(input[i + 2]); }
							catch (NumberFormatException e) { printError(input); break; } 
						}
						
						/* Initialize critters */
						for (int j = 0; j < count; j++) {
							try { Critter.makeCritter(input[i + 1]); }
							catch (InvalidCritterException e) { printError(input); break; }
						}
						
					}
					
					/* Invalid input */
					else { printError(input); }
					
					break;
				}
				
				/* Command is stats */
				else if (input[i].equals("stats")) {
					
					/* Check for name after make */
					if (i < input.length - 1) {
						
						/* Give stats on Craig */
						if (input[i + 1].equals("craig")) {
							try { Craig.runStats(Critter.getInstances(input[i + 1])); }
							catch (InvalidCritterException e) { printError(input); break; }
						}
						
						/* Give stats on Algae */
						else if (input[i + 1].equals("algae")) {
							try { Algae.runStats(Critter.getInstances(input[i + 1])); }
							catch (InvalidCritterException e) { printError(input); break; }
						}
						
						/* Invalid input */
						else { printError(input); }
						
					}
					
					/* Invalid input */
					else { printError(input); }
					
					break;
				}
				
				/* Invalid command */
				else { printInvalid(input); break; }
				
			}
			
		}
		
	}
	
	public static void printError(String[] input) {
		System.out.print("error processing: ");
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static void printInvalid(String[] input) {
		System.out.print("invalid command: ");
		for (int j = 0; j < input.length; j++) {
			System.out.print(input[j]);
			System.out.print(" ");
		}
		System.out.println();
	}
	
}
