/**
 * Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */

package pMap.phaseA;

/**
 * Map.Entry, assume that the key and value are both integers.
 */
public class Entry {
	
	private int key;
	private int value;

	public Entry(int key, int value) {
		this.key = key;
		this.value = value;
	}
	
	public int getkey(){
		return this.key;
	}
	
	public int getval(){
		return this.value;
	}
}
