/* 
 * CRITTERS Critter3.java
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

/*
 * Example critter
 */
public class Critter3 extends Critter {
	
	@Override
	public String toString() { return "3"; }
	
	
	private int dir;
	
	public Critter3() {
		
		dir = Critter.getRandomInt(3);
		if (dir ==2){
			dir = 7;
		}
	}
	
	public boolean fight(String not_used) { walk(dir);return false; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		int childdir = Critter.getRandomInt(3);
		if (childdir ==2){
			childdir = 7;
		}
		
		if (getEnergy() > 75) {
			Critter3 child = new Critter3();
			reproduce(child, childdir);
		}
		
		/* Pick a new direction based on our genes */
		
		

		
		dir = Critter.getRandomInt(3);
		if (dir ==2){
			dir = 7;
		}
	}
}