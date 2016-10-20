/**
 * Phase A <dkm989><bma862>
 * Phase B <bma862><dkm989>
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
	
	public void setkey(int key){
		this.key = key;
	}
	
	public void setval(int value){
		this.value = value;
	}
}
