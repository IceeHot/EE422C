/* 
 * Multithreading BookingClient.java
 * EE422C Project 6 submission by
 * Brent Atchison
 * bma862
 * 16455
 * Slip days used: <0>
 * Fall 2016
 */

package assignment6;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Thread;

public class BookingClient {
	
	static Theater theater;
	static Map<String, Integer> office;
	
	/**
	 * Initializes offices and theater like so:
	 * office: {BX1 = 3, BX2 = 4, BX3 = 3, BX4 = 3, BX5 = 3}
	 * theater: {3 rows, 5 seats per row, show: “Ouija"}
	 * Calls simulate()
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Theater theater = new Theater(3, 5, "Ouija");
		Map<String, Integer> office = new ConcurrentHashMap<String, Integer>();
		office.put("BX1", 3);
		office.put("BX2", 4);
		office.put("BX3", 3);
		office.put("BX4", 3);
		office.put("BX5", 3);
		BookingClient book = new BookingClient(office, theater);
		book.simulate();
	}
	
	/**
	 * @param office maps box office ID to number of customers in line
	 * @param theater the theater where the show is playing
	 */
	public BookingClient(Map<String, Integer> office, Theater theater) {
		// TODO: Implement this constructor
		office = office;
		theater = theater;
	}
	
	/**
	 * Starts the box office simulation by creating (and starting) threads
	 * for each box office to sell tickets for the given theater
	 * 
	 * @return list of threads used in the simulation,
	 * should have as many threads as there are box offices
	 */
	public List<Thread> simulate() {
		//TODO: Implement this method
		List<Thread> threadList = new ArrayList<Thread>();
		return threadList;
	}
}
