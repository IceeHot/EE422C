/* 
 * CRITTERS Brent.java
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
public class Brent extends Critter {
	
	@Override
	public String toString() { return "B"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Brent() {
		for (int k = 0; k < 8; k += 1) { genes[k] = GENE_TOTAL / 8; }
		dir = Critter.getRandomInt(5);
	}
	
	public boolean fight(String not_used) { return false; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		
		if (getEnergy() > 150) {
			Brent child = new Brent();
			for (int k = 0; k < 8; k += 1) { child.genes[k] = this.genes[k]; }
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) { g = Critter.getRandomInt(8); }
			child.genes[g] -= 1;
			g = Critter.getRandomInt(5);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(5));
		}
		
		/* Pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 5;
	}
}
