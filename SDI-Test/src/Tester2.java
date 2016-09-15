import static org.junit.Assert.*;

import org.junit.Test;

public class Tester2 {

	@Test
	public void testArray() {
		Calculator calc = new Calculator();
		int[] expectedArray = {1, 2, 3};
		assertArrayEquals(expectedArray, calc.sequence());
	}

}
