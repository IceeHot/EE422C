package assignment3;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;
public class SameWordTest {

	@Test
	public void test() {
		ArrayList<String> result = Main.getWordLadderDFS("heart", "heart");
		ArrayList<String> result2 = Main.getWordLadderBFS("heart", "heart");
		assertEquals(true, result == null && result2 == null);
		}
}
