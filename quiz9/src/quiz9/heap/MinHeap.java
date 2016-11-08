/* 
 * EE422C Quiz 9 submission by
 * Brent Atchison
 * 16445
 * bma862
 */

package quiz9.heap;

public class MinHeap {
	int size = 0;
	int[] queue = new int[100];// we assume the heap will have no more
								// than 100 elements.

	/**
	 * Inserts the specified element into this MinHeap.
	 */
	public void add(int value) {
		queue[size++] = value;
		heapSort();
	}

	public void heapSort() {
		int current = size - 1;
		if (current % 2 == 0) { return; }
        while (queue[current] < queue[current / 2]) {
            swap(current, current / 2);
            current = current / 2;
        }	
	}

	public void remove(int o) {
		// TODO you are not required to implement this method using iteration.
		// But you will get 5 points bonus if you implement it successfully.
		size--;
	}
    
    private void swap(int pos1, int pos2) {
    	queue[pos1] = queue[pos1] ^ queue[pos2];
    	queue[pos2] = queue[pos1] ^ queue[pos2];
    	queue[pos1] = queue[pos1] ^ queue[pos2];
    }
}
