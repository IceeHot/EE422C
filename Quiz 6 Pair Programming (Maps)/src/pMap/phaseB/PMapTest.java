/**
 * Phase A <dkm989><bma862>
 * Phase B <bma862><dkm989>
 */

package pMap.phaseB;

import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;

import static org.junit.Assert.*;

import org.junit.Test;

public class PMapTest {

	@Test
	public void test1() {
		PMap map = new PMap();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		System.out.println(map.size());
		Set<Integer> keys = map.keySet();
		for (Integer i : keys) {
			System.out.print(i + " ");
		}
		System.out.println();
		Collection<String> values = map.values();
		for (String i : values) {
			System.out.print(i + " ");
		}
		System.out.println(map.size());
	}
	
	@Test
	public void test2() {
		PMap map = new PMap();
		assertEquals(map.size(), 0);
		map.put(1, "one");
		assertEquals(map.size(), 1);
		assertEquals(map.containsKey(1), true);
		assertEquals(map.containsValue("one"), true);
		assertEquals(map.containsKey(3), false);
		assertEquals(map.containsValue("two"), false);
		map.remove(1);
		
	}
	
	@Test
	public void test3(){
		PMap map = new PMap();
		map.put(1, "one");
		map.remove(1);
		assertEquals(map.size(), 0);
		assertEquals(map.containsKey(1), false);
		assertEquals(map.containsValue("one"), false);
		
	}
	@Test
	public void test4(){
		PMap map = new PMap();
		map.put(1, "one");
		map.put(1, "none");
		assertEquals(map.size(), 1);
		assertEquals(map.get(1), "none");
	}
	
	@Test
	public void test5(){
		PMap map = new PMap();
		map.put(1, "one");
		map.put(1, "none");
		map.put(2,"two");
		map.put(5, "5");
		assertEquals(map.size(), 3);
		map.clear();
		assertEquals(map.size(), 0);
	}
	
	@Test
	public void test6(){
		PMap map = new PMap();
		map.put(1, "one");
		map.put(1, "none");
		map.put(2,"two");
		map.put(5, "5");
		Set<Integer> test = map.keySet();
		int[] reference = { 1, 2, 5 };
		assertEquals(test.size(), 3);
		for(int i = 0; i<test.size();i++){
			assertEquals(test.contains(reference[i]), true);
		}
	}
	
	@Test
	public void test7() {
		PMap map = new PMap();
		map.put(1, "one");
		map.put(1, "none");
		map.put(2,"two");
		map.put(5, "5");
		Collection<String> test = map.values();
		String[] ref = { "none", "5", "two", "one" };
		assertEquals(test.size(),3);
		for(int i = 0; i < test.size(); i++) {
			assertEquals(test.contains(ref[i]), true);
		}
	}
	
	@Test
	public void test8() {
		PMap map = new PMap();
		map.put(1, "one");
		map.put(1, "NotOne");
		map.put(2,"two");
		map.put(5, "Five");
		PMap map1 = new PMap();
		map1.putAll(map);
		Set<Entry<Integer, String>> test = map.entrySet();
		assertEquals(map.size(),map1.size());
		for(Entry<Integer, String> E : test) {
			assertEquals(map.containsKey(E.getKey()), true);
			assertEquals(map.get(E.getKey()).equals(E.getValue()), true);
		}
	}
	
	// TODO add more test cases to test all implemented methods
}
