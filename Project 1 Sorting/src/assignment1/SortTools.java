/*
 * SortTools.java
 * EE422C Project 1 submission by
 * Brent Atchison
 * bma862
 * 16455
 * Fall 2016
 */

package assignment1;
public class SortTools {
	
	
	/**
	 * Tests if array is sorted and returns true or false
	 * @param x is given array
	 * @param n is number of elements in x to check
	 * @return true if first n elements of array x are sorted in non-decreasing order
	 * @return false if n elements of array are unsorted
	 */
	public static boolean isSorted(int[] x, int n) {	
		
		/* Check for -n or n = 0 */
		if (n < 1){ return false; }
		
		for (int i = 0; i < n - 1; i++){
			if (x[i] > x[i + 1]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param x is given array
	 * @param n is number of elements in x to check
	 * @param v is number to look for
	 * @return index of found item
	 * @return -1 if item not found
	 */
	public static int find(int[] x, int n, int v) {
		
		int ldex = 0;
		int hdex = n - 1;
		
		/* Check ends of x first */
		if (x[hdex] == v){ return hdex; }
		else if (x[ldex] == v){ return ldex; }
		
		while (ldex <= hdex){
			int mdex = ldex + (hdex - ldex) / 2;
			if (v < x[mdex]){ hdex = mdex - 1; }
			else if (v > x[mdex]){ ldex = mdex + 1; }
			else { return mdex; }
		}
		return -1;
	}
	
	/**
	 * @param x is given array
	 * @param n is number of elements in x
	 * @param v is number to insert
	 * @return array with v included in x
	 */
	public static int[] insertGeneral(int[] x, int n, int v) {
		
		/* Check if v is already in x */
		if ((find(x, n, v)) + 1 > 0){ return x; }
		
		/* v not found in x, will be returning this new array */
		int[] newArray = new int[n + 1];
		
		/* Dumb way to check if v has been copied */
		boolean firstTime = true;
		
		for (int i = 0; i < n; i++){
			if (x[i] < v){
				newArray[i] = x[i];
			}
			else if (x[i] > v){
				/* Check if v is already copied over */
				if (firstTime){
					newArray[i] = v;
					firstTime = false;
				}
				newArray[i + 1] = x[i];
			}
		}
		
		/* v is at end */
		if (firstTime){
			newArray[n] = v;
		}
		
		return newArray;
	}
	
	/**
	 * @param x is given array
	 * @param n is number of elements in x
	 * @param v is number to insert into x
	 * @return number of elements in modified x
	 */
	public static int insertInPlace(int[] x, int n, int v) {
		
		/* Check if v is already in x */
		if ((find(x, n, v)) + 1 > 0){ return n; }
		
		int temp1 = 0;
		int temp2 = 0;
		boolean end = true;
		
		for (int i = 0; i < n; i++){
			if (x[i] == v){
				return n;
			}
			else if (x[i] > v){
				end = false;
				temp1 = x[i];
				x[i] = v;
				i++;
				while (i < n){
					temp2 = x[i];
					x[i] = temp1;
					temp1 = temp2;
					i++;
				}
				break;
			}
		}
		
		/* v is at end of x */
		if (end){
			x[n] = v;
		}
		
		return n + 1;
	}
	
	/**
	 * Sorts first n elements of array x in non-decreasing order
	 * @param x is given array
	 * @param n is number of elements in x
	 */
	public static void insertSort(int[] x, int n) {		
		int temp = 0;
		for (int i = 0; i < n; i++){
			for (int j = i; j > 0; j--){
				if (x[j] < x[j - 1]){
					temp = x[j];
					x[j] = x[j - 1];
					x[j - 1] = temp;
				}
			}
		}
		return;
	}
}
