/* 
 * CRITTERS Dhruv.java
 * EE422C Project 4 submission by
 * Dhruv Atchison
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
public class Dhruv extends Critter {
	
	@Override
	public String toString() { return "D"; }
	
	
	private int dir;
	
	public Dhruv() {
		
		dir = Critter.getRandomInt(3);
		if (dir ==2){
			dir = 7;
		}
	}
	
	public boolean fight(String not_used) { return false; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		int childdir = Critter.getRandomInt(3);
		if (childdir ==2){
			childdir = 7;
		}
		
		if (getEnergy() > 75) {
			Dhruv child = new Dhruv();
			reproduce(child, childdir);
		}
		
		/* Pick a new direction based on our genes */
		
		

		
		dir = Critter.getRandomInt(3);
		if (dir ==2){
			dir = 7;
		}
	}
}