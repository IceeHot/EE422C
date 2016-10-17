/* 
 * CRITTERS Mathew.java
 * EE422C Project 4 submission by
 * Brent Atchison
 * bma862
 * 16455
 * Mathew Mathew
 * dkm989
 * 16455
 * Slip days used: <0>
 * Fall 2016
 */

package assignment4;

/*
 * Example critter
 */
public class Mathew extends Critter {
	
	@Override
	public String toString() { return "M"; }
	
	
	private int dir;
	
	public Mathew() {
		
		dir = 3+ Critter.getRandomInt(3);
	}
	
	public boolean fight(String not_used) { return true; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		
		if (getEnergy() > 150) {
			Mathew child = new Mathew();
			
			reproduce(child, 3+Critter.getRandomInt(3));
		}
		
		/* Pick a new direction based on our genes */
		dir =3+ Critter.getRandomInt(3);
	}
}