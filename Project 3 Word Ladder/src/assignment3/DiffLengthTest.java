package assignment3;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

public class DiffLengthTest {

	@Test
	public void test() {
		ArrayList<String> tester = new ArrayList<String>();
		tester.add("heart");
		tester.add("toilet");
		ArrayList<String> result = Main.getWordLadderDFS("heart", "toilet");
		ArrayList<String> result2 = Main.getWordLadderBFS("heart", "toilet");
		assertEquals(true, result == null && result2 == null);
		tester.clear();
		
		tester.add("loves");
		tester.add("babies");
		result = Main.getWordLadderDFS("loves", "babies");
		result2 = Main.getWordLadderBFS("loves", "babies");
		assertEquals(true, result == null && result2 == null);
	}

}
