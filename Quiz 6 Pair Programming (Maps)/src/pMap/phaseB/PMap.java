/**
 * Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */
package pMap.phaseB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * PMap stands for Paired Map. A map is a collection of key value pairs, e.g.,
 * (1, "one") (2, "two") (3, "three") are all pairs with a key that's a integer and a value
 * that's a string.
 */

public class PMap implements Map<Integer,String> {
	
	private Set<Entry<Integer, String>> set = new LinkedHashSet<Entry<Integer, String>>();
	
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private ArrayList<String> values = new ArrayList<String>();
	
	@Override
	public int size() {
		return this.set.size();
	}

	@Override
	public boolean isEmpty() {
		return this.set.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		for (Entry<Integer, String> n : this.set) {
			if (n.getKey().equals(key)) { return true; }
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		for (Entry<Integer, String> n : this.set) {
			if (n.getValue().equals(value)) { return true; }
		}
		return false;
	}

	@Override
	public String get(Object key) {
		for (Entry<Integer, String> n : this.set) {
			if (n.getKey().equals(key)) { return n.getValue(); }
		}
		return null;
	}

	@Override
	public String put(Integer key, String value) {
		for (Entry<Integer, String> n : this.set) {
			if (n.getKey().equals(key)) {
				n.setValue(value);
				return value;
			}
		}
		this.set.add(new pMap.phaseB.Entry(key, value));
		return value;
	}

	@Override
	public String remove(Object key) {
		String val = null;
		for (Entry<Integer, String> n : this.set) {
			if (n.getKey().equals(key)) {
				val = n.getValue();
				this.set.remove(n);
				break;
			}
		}
		return val;
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends String> m) {
		List<Integer> ints = new ArrayList<Integer>(m.keySet());
		List<String> vals = new ArrayList<String>(m.values());
		for (int i = 0; i < ints.size(); i++) {
			this.put(ints.get(i), vals.get(i));
		}
	}

	@Override
	public void clear() {
		this.set.clear();
	}

	@Override
	public Set<Integer> keySet() {
		Set<Integer> ints = new LinkedHashSet<Integer>();
		for (Entry<Integer, String> n : this.set) { ints.add(n.getKey()); }
		return ints;
	}

	@Override
	public Collection<String> values() {
		Collection<String> vals = new LinkedHashSet<String>();
		for (Entry<Integer, String> n : this.set) { vals.add(n.getValue()); }
		return vals;
	}

	@Override
	public Set<Entry<Integer, String>> entrySet() {
		return this.set;
	}
	
}
