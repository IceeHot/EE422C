package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MatchingLettersTest {

	@Test
	public void test() {
		/*4 letters match*/
		ArrayList<String> result = Main.getWordLadderDFS("heart", "hears");
		ArrayList<String> result2 = Main.getWordLadderBFS("heart", "hears");
		ArrayList<String> answer = new ArrayList<String>();
		answer.add("heart");
		answer.add("hears");
		
		assertEquals(true, result.equals(answer) && result2.equals(answer));
		
		/*3 letters match*/
		result = Main.getWordLadderDFS("heart", "heads");
		result2 = Main.getWordLadderBFS("heart", "heads");
		assertEquals(true, (result.size() > 2)	&& (result2.size() > 2));
		
		/*2 letters match*/
		result = Main.getWordLadderDFS("heart", "herds");
		result2 = Main.getWordLadderBFS("heart", "herds");
		assertEquals(true, (result.size() > 2) && (result2.size() > 2));
		
		/*1 letter match*/
		result = Main.getWordLadderDFS("heart", "hurls");
		result2 = Main.getWordLadderBFS("heart", "hurls");
		assertEquals(true, (result.size() > 2) && (result2.size() > 2));
		
		
		
	}
}
