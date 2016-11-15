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

	static int client = 1;
	private static Theater t;
	private static Map<String, Integer> o;

	/**
	 * Initializes offices and theater like so:
	 * office: {BX1 = 3, BX2 = 4, BX3 = 3, BX4 = 3, BX5 = 3}
	 * theater: {3 rows, 5 seats per row, show: �Ouija"}
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
		o = office;
		t = theater;
	}

	public Theater getTheater() { return t; }

	public Map<String, Integer> getOffice() { return o; }

	/**
	 * Starts the box office simulation by creating (and starting) threads
	 * for each box office to sell tickets for the given theater
	 *
	 * @return list of threads used in the simulation,
	 * should have as many threads as there are box offices
	 */
	public List<Thread> simulate() {
		List<Thread> threadList = new ArrayList<Thread>();
		for (String key : this.getOffice().keySet()) {
			Show me = new Show(this, key, this.getOffice().get(key));
			Thread t = new Thread(me, key);
			threadList.add(t);
			t.run();
		}
		return threadList;
	}

	class Show implements Runnable {

		private BookingClient book;
		private String boxOffice;
		private int numCustomers;

		public Show(BookingClient newBook, String box, int customers) {
			book = newBook;
			boxOffice = box;
			numCustomers = customers;
		}

		public String getOffice() { return boxOffice; }

		public int getCustomers() { return numCustomers; }

		public void setCustomers(int customers) { numCustomers = customers; }

		public void run() {
			while (this.getCustomers() > 0 && !(book.getTheater().bestAvailableSeat() == null)) {
				book.getTheater().printTicket(boxOffice, book.getTheater().bestAvailableSeat(), client++);
				this.setCustomers(this.getCustomers() - 1);
			}
			if (book.getTheater().bestAvailableSeat() == null) {
				System.out.println("Sorry, we are sold out!");
				System.exit(0);
			}
		}
	}
}
