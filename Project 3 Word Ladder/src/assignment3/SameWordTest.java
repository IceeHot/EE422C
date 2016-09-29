package assignment3;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;
public class SameWordTest {

	@Test
	public void test() {
		ArrayList<String> result = Main.getWordLadderDFS("heart", "heart");
		ArrayList<String> result2 = Main.getWordLadderBFS("heart", "heart");
		ArrayList<String> answer = new ArrayList<String>();
		answer.add("heart");
		answer.add("heart");
		assertEquals(true, result.equals(answer) && result2.equals(answer));
		}
}
