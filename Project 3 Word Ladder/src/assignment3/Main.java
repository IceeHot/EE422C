/* 
 * Main.java
 * EE422C Project 3 (Word Ladder)
 * Brent Atchison
 * bma862
 * 16455
 * Devin Amatya
 * <Student2 EID>
 * 16455
 * Slip days used: 0
 * Git URL: https://github.com/atchisonbrent/EE422C-Project3.git
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	/* Static variables */
	public static ArrayList<String> words;
	public static ArrayList<String> BFS;
	public static ArrayList<String> DFS;
	public static int rungs;
	
	public static void main(String[] args) throws Exception {
		
		/* Input scanner for commands */
		Scanner kb;
		
		/* Output file */
		PrintStream ps;
		
		/* If arguments are specified, read/write from/to files instead of console */
		if (args.length != 0) {
			
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			
			/* Set output to PrintStream */
			System.setOut(ps);
			
		} else {
			
			/* Default console input */
			kb = new Scanner(System.in);
			
			/* Default console output */
			ps = System.out;
			
		}
		
		while (true) {

			/* Reset variables */
			initialize();
			
			/* Take keyboard input */
			parse(kb);
			
			/* Two input words are the same */
			if (words.get(0).equals(words.get(1))) { printLadder(words); }
			
			/* Two input words differ in length */
			else if (words.get(0).length() != words.get(1).length()) { printLadder(null); }
	
			/* Call and print ladder methods */
			else {
				printLadder(getWordLadderBFS(words.get(0), words.get(1)));
				printLadder(getWordLadderDFS(words.get(0), words.get(1)));
			}
		}
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		words = new ArrayList<String>();
		BFS = new ArrayList<String>();
		DFS = new ArrayList<String>();
		rungs = 0;
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		
		/* Input from Keyboard */
		words.add(keyboard.next().toUpperCase());
		
		/* Command is /quit */
		if (words.get(0).equals("/QUIT")) { System.exit(0); }
		
		words.add(keyboard.next().toUpperCase());
		
		return words;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		
		/* Create dictionary */
		Set<String> dict = makeDictionary();
		
		// TODO code
		DFS.add(start);
		
		// Fill rest of ArrayList here
		
		DFS.add(end);
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	
    	/* Create dictionary */
		Set<String> dict = makeDictionary();
		
		// TODO code
		BFS.add(start);

		// Fill rest of ArrayList here
		
		BFS.add(end);
		
		return null; // replace this line later with real return
	}
    
	public static Set<String> makeDictionary() {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
		if (ladder == null) {
			System.out.println("no word ladder can be found between "
								+ words.get(0).toLowerCase() + " and "
								+ words.get(1).toLowerCase() + ".");
		}
		else {
			System.out.println("a " + rungs + "-rung word ladder exists between "
					+ words.get(0).toLowerCase() + " and "
					+ words.get(1).toLowerCase() + ".");
			for (int i = 0; i < words.size(); i++) {
				System.out.println(words.get(i).toLowerCase());
			}
		}
	}
	// TODO
	// Other private static methods here
}
