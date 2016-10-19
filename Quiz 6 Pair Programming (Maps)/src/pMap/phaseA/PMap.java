/**
 * Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */
package pMap.phaseA;

/**
 * PMap stands for Paired Map. A map is a collection of key value pairs, e.g.,
 * (1, 2) (2, 3) (3, 4) are all pairs with a key that's a integer and a value
 * that's an integer
 */
public class PMap {
	
	private Entry[] map = new Entry[1000];
	private int length;

	private int[] keys = new int[1000];
	private int[] values = new int[1000];

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		if (length == 0) { return true; }
		return false;
	}

	public boolean containsKey(int key) {
		for (int i = 0; i < length; i++) {
			if (this.keys()[i] == key) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(int value) {
		for (int i = 0; i < length; i++) {
			if (this.values()[i] == value) {
				return true;
			}
		}
		return false;
	}

	public int get(int key) {
		for (int i = 0; i < length; i++) {
			if (this.keys()[i] == key) {
				return this.values()[i];
			}
		}
		return 0;
	}

	public int put(int key, int value) {
		int returnval = 0;
		for (int i = 0; i < length; i++) {
			if (keys[i] == key) {
				returnval = values[i];
				values[i] = value;
				map[i] = new Entry(key, value);
			}
		}
		return returnval;
	}

	public int remove(int key) {
		int removeval = 0;
		for (int i = 0; i < length;i++) {
			if (keys[i] == key){
				removeval = values[i];
				for (int j = i; j< length - 1; j++) {
					keys[j] = keys[j + 1];
					values[j] = values[j + 1];
					length -= 1;
				}
				for (int k = 0; k < length; k++){
					this.map[k] = new Entry(keys[i], values[i]);
				}
			}
		}		
		return removeval;
	}

	public void putAll(int[] keys, int[] values) {
		for (int i = 0; i < keys.length; i++) {
			this.put(keys[i], values[i]);
		}
	}

	public void clear() {
		this.map = new Entry[1000];
	}

	public int[] keys() {
		for (int i = 0; i < length; i++) {
			keys[i] = this.map[i].getkey();
		}
		return keys;
	}

	public int[] values() {
		for (int i = 0; i < length; i++) {
			values[i]=	this.map[i].getval();
			}
			return values;
	}

	public Entry[] entrys() {
		return this.map;
	}

}
