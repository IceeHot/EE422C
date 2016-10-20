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
		PMap pmap = new PMap();
		pmap.put(1, "one");
		pmap.put(2, "two");
		pmap.put(3, "three");
		System.out.println(pmap.size());
		Set<Integer> keys = pmap.keySet();
		for (Integer i : keys) {
			System.out.print(i + " ");
		}
		System.out.println();
		Collection<String> values = pmap.values();
		for (String i : values) {
			System.out.print(i + " ");
		}
		System.out.println(pmap.size());
	}
	
	@Test
	public void test2() {
		PMap pMap = new PMap();
		assertEquals(pMap.size(),0);
		pMap.put(1, "one");
		assertEquals(pMap.size(),1);
		assertEquals(pMap.containsKey(1),true);
		assertEquals(pMap.containsValue("one"),true);
		assertEquals(pMap.containsKey(3),false);
		assertEquals(pMap.containsValue("two"),false);
		pMap.remove(1);
		
	}
	
	@Test
	public void test3(){
		PMap pMap = new PMap();
		pMap.put(1, "one");
		pMap.remove(1);
		assertEquals(pMap.size(),0);
		assertEquals(pMap.containsKey(1),false);
		assertEquals(pMap.containsValue("one"),false);
		
	}
	@Test
	public void test4(){
		PMap pMap = new PMap();
		pMap.put(1, "one");
		pMap.put(1, "NotOne");
		assertEquals(pMap.size(),1);
		assertEquals(pMap.get(1),"NotOne");
	}
	
	@Test
	public void test5(){
		PMap pMap = new PMap();
		pMap.put(1, "one");
		pMap.put(1, "NotOne");
		pMap.put(2,"two");
		pMap.put(5, "Five");
		assertEquals(pMap.size(),3);
		pMap.clear();
		assertEquals(pMap.size(),0);
	}
	
	@Test
	public void test6(){
		PMap pMap = new PMap();
		pMap.put(1, "one");
		pMap.put(1, "NotOne");
		pMap.put(2,"two");
		pMap.put(5, "Five");
		Set<Integer> test = pMap.keySet();
		int[] reference = {1,2,5};
		assertEquals(test.size(),3);
		for(int i = 0; i<test.size();i++){
			assertEquals(test.contains(reference[i]),true);
		}
	}
	
	@Test
	public void test7(){
		PMap pMap = new PMap();
		pMap.put(1, "one");
		pMap.put(1, "NotOne");
		pMap.put(2,"two");
		pMap.put(5, "Five");
		Collection<String> test = pMap.values();
		String[] reference = {"NotOne","two","Five"};
		assertEquals(test.size(),3);
		for(int i =0;i<test.size();i++){
			assertEquals(test.contains(reference[i]),true);
		}
	}
	
	@Test
	public void test8(){
		PMap test = new PMap();
		test.put(1, "one");
		test.put(1, "NotOne");
		test.put(2,"two");
		test.put(5, "Five");
		PMap pMap = new PMap();
		pMap.putAll(test);
		Set<Entry<Integer, String>> test2 = pMap.entrySet();
		assertEquals(test.size(),pMap.size());
		for(Entry<Integer, String> E: test2){
			assertEquals(pMap.containsKey(E.getKey()), true);
			assertEquals(pMap.get(E.getKey()).equals(E.getValue()), true);
		}
	}
	
	// TODO add more test cases to test all implemented methods
}
