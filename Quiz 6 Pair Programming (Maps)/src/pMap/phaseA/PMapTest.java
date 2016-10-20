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
		PMap pmap = new PMap();
		pmap.put(0, 1);
		pmap.put(1, 2);
		pmap.put(2, 3);
		System.out.println(pmap.size());
		int[] keys = pmap.keys();
		for (int i : keys) {
			System.out.print(i+" ");
		}
		System.out.println();
		int[] values = pmap.values();
		for (int i : values) {
			System.out.print(i+" ");
		}
		System.out.println(pmap.size());
	}

	@Test
	public void test2(){
		PMap pMap = new PMap();
		assertEquals(pMap.remove(30),0);
	}
	@Test
	public void test3(){
		PMap pMap = new PMap();
		pMap.put(54,67);
		assertEquals(pMap.get(54), 67);
		assertEquals(pMap.put(54, 505), 67);
		assertEquals(pMap.get(54), 505);
	}
	@Test
	public void test4(){
		PMap pMap = new PMap();
		assertEquals(pMap.isEmpty(), true);
		pMap.put(1,2);
		assertEquals(pMap.isEmpty(), false);
		int test = pMap.remove(1);
		assertEquals(test, 2);
		assertEquals(pMap.isEmpty(), true);
	}
	@Test
	public void test5(){
		int[] keys = {1,2,3,4,5};
		int[] values = {1,2,3,4,5};
		PMap testmap = new PMap();
		for(int i = 4; i >= 0; i--){
			testmap.put(keys[i], values[i]);
		}
		PMap pMap = new PMap();
		pMap.putAll(keys, values);
		assertArrayEquals(keys, pMap.keys());
		assertArrayEquals(values, pMap.values());
		assertArrayEquals(keys, pMap.keys());
		Entry[] test = pMap.entrys();
		for(int  i = 0; i < pMap.size(); i++){
			assertEquals(pMap.containsKey(test[i].getkey()), true);
			assertEquals(pMap.get(test[i].getkey()) == test[i].getval(), true);
		}
	}
	@Test
	public void test6(){
		PMap pMap = new PMap();
		pMap.put(1, 2);
		pMap.clear();
		assertEquals(pMap.isEmpty(), true);
	}
}