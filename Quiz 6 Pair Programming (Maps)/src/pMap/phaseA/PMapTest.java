/**
 * Phase A <dkm989><bma862>
 * Phase B <bma862><dkm989>
 */

package pMap.phaseA;
import static org.junit.Assert.*;

import org.junit.Test;

public class PMapTest {

	@Test
	public void test1() {
		PMap map = new PMap();
		map.put(0, 1);
		map.put(1, 2);
		map.put(2, 3);
		System.out.println(map.size());
		int[] keys = map.keys();
		for (int i : keys) {
			System.out.print(i+" ");
		}
		System.out.println();
		int[] values = map.values();
		for (int i : values) {
			System.out.print(i+" ");
		}
		System.out.println(map.size());
	}

	@Test
	public void test2(){
		PMap map = new PMap();
		assertEquals(map.remove(42),0);
	}
	
	@Test
	public void test3(){
		PMap map = new PMap();
		map.put(42,24);
		assertEquals(map.get(42), 24);
		assertEquals(map.put(42, 4), 24);
		assertEquals(map.get(42), 4);
	}
	
	@Test
	public void test4(){
		PMap map = new PMap();
		assertEquals(map.isEmpty(), true);
		map.put(0,1);
		assertEquals(map.isEmpty(), false);
		int test = map.remove(0);
		assertEquals(test, 1);
		assertEquals(map.isEmpty(), true);
	}
	
	@Test
	public void test5(){
		PMap map = new PMap();
		int[] keys = { 0, 1, 2, 3, 4, 5 };
		int[] values = { 0, 1, 2, 3, 4, 5 };
		for(int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		PMap pMap = new PMap();
		pMap.putAll(keys, values);
		assertArrayEquals(keys, pMap.keys());
		assertArrayEquals(values, pMap.values());
		assertArrayEquals(keys, pMap.keys());
		Entry[] test = pMap.entrys();
		for (int i = 0; i < pMap.size(); i++) {
			assertEquals(pMap.containsKey(test[i].getkey()), true);
			assertEquals(pMap.get(test[i].getkey()) == test[i].getval(), true);
		}
	}
	
	@Test
	public void test6(){
		PMap pMap = new PMap();
		pMap.put(0, 1);
		pMap.clear();
		assertEquals(pMap.isEmpty(), true);
	}
}