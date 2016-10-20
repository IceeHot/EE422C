/* 
 * CRITTERS Critter2.java
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

public class Critter2 extends Critter {
	
	private int dir;
	
	public Critter2() { dir = Critter.getRandomInt(4) + 3; }
	
	@Override
	public String toString() { return "2"; }
	
	public boolean fight(String not_used) { return Critter.getRandomInt(3) > 0; }

	@Override
	public void doTimeStep() {
		
		/* Take two steps forward */
		run(dir);
		
		/* Check if able to reproduce */
		if (getEnergy() > 100) {
			Critter2 child = new Critter2();
			reproduce(child, Critter.getRandomInt(4) + 3);
		}
		
		/* Update direction */
		dir = Critter.getRandomInt(4) + 3;
	}
}
