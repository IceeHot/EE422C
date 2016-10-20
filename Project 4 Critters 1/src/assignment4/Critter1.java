/* 
 * CRITTERS Critter1.java
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

public class Critter1 extends Critter {
	
	private int dir;
	
	public Critter1() { dir = Critter.getRandomInt(5); }
	
	@Override
	public String toString() { return "1"; }
	
	public boolean fight(String not_used) { return Critter.getRandomInt(2) > 0; }

	@Override
	public void doTimeStep() {
		
		/* Take one step forward */
		walk(dir);
		
		/* Check if able to reproduce */
		if (getEnergy() > 200) {
			Critter1 child = new Critter1();
			reproduce(child, Critter.getRandomInt(5));
		}
		
		/* Update direction */
		dir = Critter.getRandomInt(5);
	}
}
