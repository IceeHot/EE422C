/* 
 * CRITTERS Atchison.java
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

public class Atchison extends Critter {
	
	private int dir;
	
	public Atchison() { dir = Critter.getRandomInt(5) + 3; }
	
	@Override
	public String toString() { return "A"; }
	
	public boolean fight(String not_used) { return Critter.getRandomInt(2) > 0; }

	@Override
	public void doTimeStep() {
		
		/* Take two steps forward */
		run(dir);
		
		/* Check if able to reproduce */
		if (getEnergy() > 100) {
			Atchison child = new Atchison();
			reproduce(child, Critter.getRandomInt(5) + 3);
		}
		
		/* Update direction */
		dir = Critter.getRandomInt(5) + 3;
	}
}
