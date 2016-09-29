package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BlankStringTest {

	@Test
	public void test() {
		ArrayList<String> result = Main.getWordLadderDFS("", "");
		ArrayList<String> result2 = Main.getWordLadderBFS("", "");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS("/n", "/t");
		result2 = Main.getWordLadderBFS("/n", "/t");
		assertEquals(true, result == null && result2 == null);
		
		result = Main.getWordLadderDFS(" ", "/t");
		result2 = Main.getWordLadderBFS(" ", "/t");
		assertEquals(true, result == null && result2 == null);
	}
}
