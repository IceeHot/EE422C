/* 
 * CRITTERS Atchison.java
 * EE422C Project 4 submission by
 * Atchison Atchison
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
public class Atchison extends Critter {
	
	@Override
	public String toString() { return "A"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Atchison() {
		for (int k = 0; k < 8; k += 1) { genes[k] = GENE_TOTAL / 8; }
		dir = Critter.getRandomInt(5) + 3;
	}
	
	public boolean fight(String not_used) { return true; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(dir);
		
		if (getEnergy() > 150) {
			Atchison child = new Atchison();
			for (int k = 0; k < 8; k += 1) { child.genes[k] = this.genes[k]; }
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) { g = Critter.getRandomInt(8); }
			child.genes[g] -= 1;
			g = Critter.getRandomInt(5) + 3;
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(5) + 3);
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
