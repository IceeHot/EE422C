/**
 * Phase A <dkm989><bma862>
 * Phase B <bma862><dkm989>
 */

package pMap.phaseA;

/**
 * PMap stands for Paired Map. A map is a collection of key value pairs, e.g.,
 * (1, 2) (2, 3) (3, 4) are all pairs with a key that's a integer and a value
 * that's an integer
 */
public class PMap {
	
	private Entry[] map = new Entry[1000];
	private int length = 0;

	public int size() { return length; }

	public boolean isEmpty() { return length == 0; }

	public boolean containsKey(int key) {
		for (int i = 0; i < length; i++) {
			if (map[i].getkey() == key) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(int value) {
		for (int i = 0; i < length; i++) {
			if (map[i].getval() == value) {
				return true;
			}
		}
		return false;
	}

	public int get(int key) {
		for (int i = 0; i < length; i++) {
			if (map[i].getkey() == key) {
				return map[i].getval();
			}
		}
		return 0;
	}

	public int put(int key, int value) {
		if (!containsKey(key)) {
			map[length] = new Entry(key, value);
			length++;
			return value;
		}
		for(int i = 0; i < length; i++) {
			if (map[i].getkey() == key) {
				int j = map[i].getval();
				map[i].setval(value);
				return j;
			}
		}
		return 0;
	}

	public int remove(int key) {
		int retval = 0;
		
		if (!this.containsKey(key)) { return retval; }
		/*
		if (length == 1) {
			retval = map[retval].getval();
			length--;
			return retval;
		}*/
		
		for (int i = 0; i < length; i++) {
			if (map[i].getkey() == key) {
				retval = map[i].getval();
				map[i] = map[length - 1];
				length--;
				return retval;
			}
		}
		return retval;
	}

	public void putAll(int[] keys, int[] values) {
		for (int i = 0; i < keys.length; i++) {
			this.put(keys[i], values[i]);
		}
	}

	public void clear() {
		map = new Entry[1000];
		length = 0;
	}

	public int[] keys() {
		int[] ints = new int[length];
		for (int i = 0; i < length; i++) {
			ints[i] = map[i].getkey();
		}
		return ints;
	}

	public int[] values() {
		int[] vals = new int[length];
		for (int i = 0; i < length; i++) {
			vals[i] = map[i].getkey();
		}
		return vals;
	}

	public Entry[] entrys() {
		return map;
	}

}
