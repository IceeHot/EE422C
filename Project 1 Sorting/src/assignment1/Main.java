/* 
 * This file is how you might test out your code.  Don't submit this, and don't 
 * have a main method in SortTools.java.
 */

package assignment1;
import java.util.Arrays;
public class Main {
	public static void main(String [] args) {
		
		int[] array = new int[10];
		
		for (int i = 0; i < 9; i++){
			array[i] = (int)(Math.random() * 1000);
		}
		
///		int[] array = {1,2,4,7,8,9,10,11,12,13,0};
		
		System.out.println(Arrays.toString(array));
		System.out.println("Is sorted: " + SortTools.isSorted(array, 1));

		System.out.println("Index of found number: " + SortTools.find(array, 10, 0));
		
		SortTools.insertSort(array, 10);
		
		System.out.println(Arrays.toString(SortTools.insertGeneral(array, 10, 343)));
		
		SortTools.insertInPlace(array, 10, 344);
		
		System.out.println(Arrays.toString(array));
		System.out.println("Is sorted: " + SortTools.isSorted(array, 10));
	}
}
