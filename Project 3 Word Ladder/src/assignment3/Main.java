/* 
 * Main.java
 * EE422C Project 3 (Word Ladder)
 * Brent Atchison
 * bma862
 * 16455
 * Devin Amatya
 * dga383
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
	public static Queue<ArrayList<String>> queue;
	public static ArrayList<String> usedWords;
	public static ArrayList<String> words;
	public static ArrayList<String> DFS;
	public static Set<String> dict;
	public static boolean found;
	
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

		initialize();
		
		/* Take keyboard input */
		parse(kb);
		
		/* Print BFS ladder */
		printLadder(getWordLadderBFS(words.get(0), words.get(1)));
		System.out.println();
		
		/* Print DFS ladder */
		printLadder(getWordLadderDFS(words.get(0), words.get(1)));
		System.out.println();
	}
	
	/**
	 * Useless
	 */
	public static void initialize() {
		queue = new LinkedList<ArrayList<String>>();
		usedWords = new ArrayList<String>();
		words = new ArrayList<String>();
		DFS = new ArrayList<String>();
		dict = makeDictionary();
		found = false;
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		
		/* Clear prior input before taking more */
		words.clear();
		
		/* Input two words from Keyboard */
		words.add(keyboard.next().toUpperCase());
		if (words.get(0).equals("/QUIT")) { System.exit(0); }
		words.add(keyboard.next().toUpperCase());
		if (words.get(1).equals("/QUIT")) { System.exit(0); }
		
		return words;
	}
	
	/**
	 * Produce word ladder using DFS
	 * @param start is beginning word
	 * @param end is ending word
	 * @return found word ladder
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		/* Reset everything before starting */
		reset();
		words.add(start.toLowerCase());
		words.add(end.toLowerCase());
		start = start.toUpperCase();
		end = end.toUpperCase();
		
		/* Ensure words are in dictionary */
		if (!dict.contains(start) || !dict.contains(end)) { return null; }
		
		/* Check for differing word length */
		if (start.length() != end.length()) { return null; }
		
		/* Check if neighbors */
		else if (isNeighbor(start, end)) { return words; }
		
		/* Add starting word to beginning */
		ArrayList<String> begin = new ArrayList<String>();
		begin.add(start);
		
		/* Add starting word to used words */
		usedWords.add(start);
		
		/* Call recursive helper method */
		DFSHelper(start, end, begin);
		
		/* Optimize word ladder */
		DFS = optimize(DFS);
		
		return DFS;
		
	}
	
	/**
	 * Recursive helper to getWordLadderDFS
	 * @param start is starting word
	 * @param end is ending word
	 * @param begin is starting ArrayList
	 */
	private static void DFSHelper(String start, String end, ArrayList<String> begin) {
		
		/* Found a valid ladder */
		if (found) { return; }
		
		/* Last word is end word */
		if (begin.get(begin.size() - 1).equals(end)) {
			found = true;
			DFS = new ArrayList<String>(begin);
			return;
		}
		
		/* Iterate while dictionary has remaining objects */
		for (Iterator<String> i = dict.iterator(); i.hasNext();) {
			
			/* Next item in dictionary */
			String next = i.next();
			
			/* Check for one letter difference and not containing used word */
			if (isNeighbor(next, begin.get(begin.size() - 1))
					&& !begin.contains(next)
					&& !usedWords.contains(next)) {
				
				/* New ladder for recursive call */
				begin.add(next);
				
				/* Add word to used list */
				usedWords.add(next);
				
				/* Recursive call with new beginning */
				DFSHelper(start, end, begin);
				
				/* Found a valid ladder */
				if (found) { return; }
				
				/* Remove last element from begin */
				begin.remove(begin.size() - 1);
			}
		}
	}
    		
	/**
	 * Produce word ladder using BFS
	 * @param start is beginning word
	 * @param end is ending word
	 * @return found word ladder
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	
    	/* Reset everything before starting */
    	reset();
		words.add(start.toLowerCase());
		words.add(end.toLowerCase());
		start = start.toUpperCase();
		end = end.toUpperCase();
		
		/* Ensure words are in dictionary */
		if (!dict.contains(start) || !dict.contains(end)) { return null; }
    	
    	/* Check for differing word length */
		if (start.length() != end.length()) { return null; }
		
		/* Check if neighbors */
		else if (isNeighbor(start, end)) { return words; }
		
		/* Remove start word from dictionary */
		dict.remove(start);
		
		/* Store starting word to new ArrayList */
		ArrayList<String> startList = new ArrayList<String>();
		startList.add(start);
		
		/* Store starting ArrayList to be searched */
		queue.add(startList);
		
		while (!queue.isEmpty() && !queue.peek().equals(end)) {
			
			/* Take first item out of queue */
			ArrayList<String> list = queue.remove();
			
			/* Check if last element of list is end */
			if (list.get(list.size() - 1).equals(end)) { return list; }
			
			/* Continue while words are left in the dictionary */
			for (Iterator<String> i = dict.iterator(); i.hasNext();) {
				
				/* Next word from dictionary */
				String next = i.next();
				
				/* Check if next word and last word are one letter different */
				if (isNeighbor(next, list.get(list.size() - 1))) {
					
					/* New list to add to queue */
					ArrayList<String> newList = new ArrayList<String>(list);
					
					/* Add word that differs by only one letter */
					newList.add(next);
					
					/* Add newList back to queue */
					queue.add(newList);
					
					/* Remove used word from dictionary */
					i.remove();
				}
			}
		}
		
		/* Return next element of queue */
	    return queue.peek();
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
	
	/**
	 * Prints ladder to console
	 * @param ladder is ArrayList to print
	 */
	public static void printLadder(ArrayList<String> ladder) {
		
		if (ladder == null || ladder.size() == 0 ) {
			System.out.println("no word ladder can be found between "
								+ words.get(0).toLowerCase() + " and "
								+ words.get(1).toLowerCase() + ".");
		}
		else {
			System.out.println("a " + (ladder.size() - 2)
					+ "-rung word ladder exists between "
					+ ladder.get(0).toLowerCase() + " and "
					+ ladder.get(ladder.size() - 1).toLowerCase() + ".");
			for (int i = 0; i < ladder.size(); i++) {
				System.out.println(ladder.get(i).toLowerCase());
			}
		}
	}
	
	/**
	 * Reset variables
	 */
	private static void reset() {
		queue = new LinkedList<ArrayList<String>>();
		usedWords = new ArrayList<String>();
		words = new ArrayList<String>();
		DFS = new ArrayList<String>();
		dict = makeDictionary();
		found = false;
	}

	/**
	 * Check if two strings differ by one letter
	 * @param a first word
	 * @param b second word
	 * @return true if words differ by one letter or less
	 */
	private static boolean isNeighbor(String a, String b) {
		
		int differs = 0;
		
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) { differs++; }
			if (differs > 1) { return false; }
		}
		
		return true;
	}
	
	/**
	 * Optimizes word ladder
	 * @param ladder to optimize
	 * @return optimized word ladder
	 */
	private static ArrayList<String> optimize(ArrayList<String> ladder) {
		
		/* Continues while still not optimized */
		while (!isOptimized(ladder)) {
			
			/* Loop starts at first word */
			for (int i = 0; i < ladder.size() - 2; i++) {
				
				/* Loop starts at third word */
				for (int j = i + 2; j < ladder.size(); j++) {
					
					/* If i is neighbor to j */
					if (isNeighbor(ladder.get(i), ladder.get(j))) {
						
						/* Remove all elements in between i and j */
						ladder.subList(i + 1, j).clear();
					}
				}
			}
		}
		
		/* Returns optimized ladder */
		return ladder;
	}
	
	/**
	 * Checks if word ladder is optimized
	 * @param ladder to check
	 * @return true if optimized
	 */
	private static boolean isOptimized(ArrayList<String> ladder) {
		
		/* Loop starts at first word */
		for (int i = 0; i < ladder.size() - 2; i++) {
			
			/* Loop starts at third word */
			for (int j = i + 2; j < ladder.size(); j++) {
				
				/* If i is neighbor to j */
				if (isNeighbor(ladder.get(i), ladder.get(j))) {
					
					/* Ladder is not yet optimized */
					return false;
				}
			}
		}
		
		/* Ladder is optimized */
		return true;
	}
}
