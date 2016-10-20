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

	public int size() { return map.length; }

	public boolean isEmpty() { return map.length == 0; }

	public boolean containsKey(int key) {
		for (int i = 0; i < map.length; i++) {
			if (this.map[i].getkey() == key) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(int value) {
		for (int i = 0; i < map.length; i++) {
			if (this.map[i].getval() == value) {
				return true;
			}
		}
		return false;
	}

	public int get(int key) {
		for (int i = 0; i < map.length; i++) {
			if (this.map[i].getkey() == key) {
				return this.map[i].getval();
			}
		}
		return 0;
	}

	public int put(int key, int value) {
		int index = 0;
		boolean found = false;
		if (!this.isEmpty()) {
			for (index = 0; index < map.length; index++) {
				if (this.map[index].getkey() == key) {
					found = true;
					break;
				}
			}
		}
		if (!found) { return 0; }
		this.map[index].setkey(key);
		this.map[index].setval(value);
		return value;
	}

	public int remove(int key) {
		int retval = 0;
		if (!this.containsKey(key)) { return retval; }
		
		Entry[] newMap = new Entry[1000];
		
		for (int i = 0; i < map.length - 1; i++) {
			if (map[i].getkey() == key) {
				retval = map[i].getval();
				i++;
			}
			newMap[i] = map[i];
		}
		map = newMap;
		return retval;
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
		int[] ints = new int[1000];
		for (int i = 0; i < map.length; i++) {
			ints[i] = this.map[i].getkey();
		}
		return ints;
	}

	public int[] values() {
		int[] vals = new int[1000];
		for (int i = 0; i < map.length; i++) {
			vals[i] = this.map[i].getkey();
		}
		return vals;
	}

	public Entry[] entrys() {
		return this.map;
	}

}
