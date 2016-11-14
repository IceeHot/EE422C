/*
 * Multithreading Theater.java
 * EE422C Project 6 submission by
 * Brent Atchison
 * bma862
 * 16455
 * Slip days used: <0>
 * Fall 2016
 */

package assignment6;

import java.util.*;

public class Theater {

	private int rows;
	private int seats;
	private String show;
	static List<Ticket> log = new ArrayList<Ticket>();

	/**
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() { return seatNum; }

		public int getRowNum() { return rowNum; }

		@Override
		public String toString() { return toLetters(this.getRowNum()) + seatNum; }
	}

	/**
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
		private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() { return seat; }

		public String getShow() { return show; }

		public String getBoxOfficeId() { return boxOfficeId; }

		public int getClient() { return client; }

		@Override
		public String toString() {

			/* Ticket lines */
			String edge = "-";
			String line1 = "| Show: " + this.getShow();
			String line2 = "| Box Office ID: " + this.getBoxOfficeId();
			String line3 = "| Seat: " + this.getSeat().toString();
			String line4 = "| Client: " + this.getClient();

			/* Find necessary ticket length */
			int tLength = line1.length() + 1;
			if (line2.length() > tLength) { tLength = line2.length() + 1; }
			if (line3.length() > tLength) { tLength = line3.length() + 1; }
			if (line4.length() > tLength) { tLength = line4.length() + 1; }

			/* Extend tickets to proper length */
			for (int i = 0; i < tLength; i++) { edge += "-"; }
			for (int i = line1.length(); i < tLength; i++) { line1 += " "; }
			for (int i = line2.length(); i < tLength; i++) { line2 += " "; }
			for (int i = line3.length(); i < tLength; i++) { line3 += " "; }
			for (int i = line4.length(); i < tLength; i++) { line4 += " "; }

			/* End ticket correctly */
			edge += "\n";
			line1 += "|\n";
			line2 += "|\n";
			line3 += "|\n";
			line4 += "|\n";

			return edge + line1 + line2 + line3 + line4 + edge;
		}
	}

	/**
	 * Constructs a new theater
	 *
	 * @param numRows is number of rows
	 * @param seatsPerRow is number of seats per row
	 * @param show is the name of the show
	 */
	public Theater(int numRows, int seatsPerRow, String show) {
		this.rows = numRows;
		this.seats = seatsPerRow;
		this.show = show;
	}

	public int getRows() { return rows; }

	public int getSeats() { return seats; }

	public String getShow() { return show; }

	/**
	 * Calculates the best seat not yet reserved
	 *
	 * @return the best seat or null if theater is full
	 */
	public Seat bestAvailableSeat() {
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getSeats(); j++) {
				if (log.isEmpty()) { return new Seat(i, j); }
				for (Ticket t : log) {
					Seat seat = new Seat(i, j);
					if (t.getSeat().toString().equals(seat.toString())) { continue; }
					else { return seat; }
				}
			}
		}
		return null;
	}

	/**
	 * Prints a ticket for the client after they reserve a seat
	 * Also prints the ticket to the console
	 *
	 * @param boxOfficeId that sold the ticket
	 * @param seat a particular seat in the theater
	 * @param client who bought the ticket
	 * @return a ticket or null if a box office failed to reserve the seat
	 */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		if (seat == null) { return null; }
		Ticket ticket = new Ticket(this.getShow(), boxOfficeId, seat, client);
		System.out.println(ticket.toString());
		log.add(ticket);
		return ticket;
	}

	/**
	 * Lists all tickets sold for this theater in order of purchase
	 *
	 * @return list of tickets sold
	 */
	public List<Ticket> getTransactionLog() { return log; }

	/**
	 * Convert from base 10 to base 26
	 *
	 * @param num number to convert
	 * @return string representation
	 */
	public static String toLetters(int num) {
		String result = "";
	    while (num > 0) {
	    	int rem = --num % 26;
	    	char letter = (char) (rem + 'A');
	    	result = letter + result;
	    	num = (num - rem) / 26;
	    }
	    return result;
	  }
}
