package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class NotInDictionaryTest {

	@Test
	public void test() {
		ArrayList<String> result = Main.getWordLadderDFS("a", "c");
		ArrayList<String> result2 = Main.getWordLadderBFS("a", "c");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS("sdbds", "dskcs");
		result2 = Main.getWordLadderBFS("sdbds", "dskcs");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS("heart", "dskcs");
		result2 = Main.getWordLadderBFS("heart", "dskcs");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS("heart", "dskcs");
		result2 = Main.getWordLadderBFS("heart", "dskcs");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS("sdbds", "blast");
		result2 = Main.getWordLadderBFS("sdbds", "blast");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS(",", ".");
		result2 = Main.getWordLadderBFS("," ,".");
		assertEquals(true, result == null && result2 == null);
		
		
	}

}
