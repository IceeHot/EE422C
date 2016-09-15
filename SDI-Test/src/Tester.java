import static org.junit.Assert.*;
import java.util.Random;

import org.junit.Test;

public class Tester {

	@Test
	public void addTest() {
		
		Random rand = new Random();
		
		int large = (int) Math.pow(2, 31) - 1;
		int half = (int) Math.pow(2, 31) / 2;
		
		/* Random integers */
		int a = rand.nextInt(large) - half;
		int b = rand.nextInt(large) - half;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		Calculator calc = new Calculator();
		int actualResult = calc.add(a, b);
		
		System.out.println("a + b = " + actualResult);
		
		/* Test assertions */
		assertEquals(a + b, actualResult);
	}
	
	@Test
	public void nullTest() {
		assertEquals(null, new Calculator().getNull());
	}

}
