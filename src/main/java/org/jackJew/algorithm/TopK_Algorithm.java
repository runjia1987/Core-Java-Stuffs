package org.jackJew.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * top K algorithm
 * 
 * @author Jack
 *
 */
public class TopK_Algorithm {

	private final int Range = 9999999;

	/**
	 * create a random string array
	 * 
	 * @param n
	 *            length
	 * @return
	 */
	public int[] createRandomString(int n) {
		int[] array = new int[n];
		int i = 0;
		Random rand = new Random();
		while (i < n) {
			rand.setSeed(i * 10);
			array[i++] = rand.nextInt(Range);
		}

		return array;
	}

	public void shiftArray(int[] array, int srcIndex, int destIndex, int length) {
		System.arraycopy(array, srcIndex, array, destIndex, length);
	}

	/**
	 * use k-size array and array shifting to find top k asc
	 * 
	 * @param array
	 * @param k
	 */
	public void getTopKByArray(int[] array, int k) {
		int element = 0, index = 0, max = array.length;
		int[] store = new int[k];
		System.arraycopy(array, 0, store, 0, k);
		index = k;

		System.out.println("the first k elements: " + Arrays.toString(store));
		// asc sort the store array
		int a, b;
		for (a = 0; a < k - 1; a++) {
			int c1 = store[a], indexB = a;
			for (b = a + 1; b < k; b++) {
				if (store[b] < c1) {
					c1 = store[indexB = b];
				}
			}
			store[indexB] = store[a];
			store[a] = c1;
		}
		System.out.println("after asc sort: " + Arrays.toString(store));

		// iterator over the rest
		while (index < max) {
			element = array[index++];
			if (element > store[0]) {
				// replace first node and shift array
				// find the index
				int j = 1;
				while (j < k) {
					if (store[j++] > element) {
						j--;
						break;
					}
				}
				j--;
				shiftArray(store, 1, 0, j);
				store[j] = element;
			}
		}
		// print the store arrays holding top K elements;
		System.out.println("getTopKByArray final result: " + Arrays.toString(store));
	}

	/**
	 * use min heap and sift up & down to find top k asc <br>
	 * time cost: n*logK
	 */
	public void getTopKByMinHeap(int[] array, int k) {
		int i = 0, index = 0, max = array.length;
		int[] store = new int[k];
		// set up k-size array
		while (i < k) {
			if (i == 0) {
				store[0] = array[index];
			} else {
				siftUp(store, i, array[index]);
			}
			i++;
			index++;
		}

		System.out.println("k-size heap is setUp: " + Arrays.toString(store));

		while (index < max) {
			int element = array[index++];
			if (element > store[0]) {
				siftDown(store, 0, element);
			}
		}
		System.out.println("getTopKByMinHeap final result: " + Arrays.toString(store));
	}

	/**
	 * @see java.util.PriorityQueue#siftUp
	 * @param i
	 * @param element
	 */
	public void siftUp(int[] array, int i, int element) {
		int parent = 0;
		while (i > 0) {
			parent = i >>> 1;
			if (element >= array[parent]) {
				break;
			}
			// parent is greater than children
			array[i] = array[parent];
			i = parent;
		}
		array[i] = element;
	}

	/**
	 * @see java.util.PriorityQueue#siftDown
	 * @param array
	 * @param i
	 * @param element
	 */
	public void siftDown(int[] array, int i, int element) {
		int child1 = 0, child2 = 0;
		int threshold = array.length >>> 1;
		while (i < threshold) {
			child1 = (i << 1) + 1;
			int childElement = array[child1];
			child2 = child1 + 1;
			// find the least child
			if (child2 < array.length && array[child2] < childElement) {
				childElement = array[child1 = child2];
			}
			if (element <= childElement) {
				break;
			}
			array[i] = childElement;
			i = child1;
		}
		array[i] = element;
	}
	
	public void getTopKByQuickSort(int[] array, int k, int left, int right) {
		if( left < right) {
			int index = findPivotIndex(array, left, right);
			//System.out.println(index + ": " + k);
			if(index == k - 1) {
				// found
				for (int i = 0; i <= index; i++) {
					//System.out.print(array[i] + ", ");
				}
			} else if(index > k - 1){
				getTopKByQuickSort(array, k, 0, index - 1);
			} else {
				getTopKByQuickSort(array, k, index + 1, array.length - 1);
			}
		} else {
			System.out.println(left + "," + right);
		}
	}
	
	// desc sort
	private int findPivotIndex(int[] array, int left, int right){
		int i = left, j = right;
		final int pivot = array[left];
		while(i < j){
			while(i < j && array[j] <= pivot) {
				j--;
			}
			if(i < j)
				array[i] = array[j];
			while(i < j && array[i] > pivot) {
				i++;
			}
			if(i < j)
				array[j] = array[i];
		}
		array[i] = pivot;
		return i;
	}

	public static void main(String[] args) {
		TopK_Algorithm ta = new TopK_Algorithm();
		int[] array = ta.createRandomString(100000);
		// System.out.println(Arrays.toString(array));

		// by array
		long startTime = System.nanoTime();
		ta.getTopKByArray(array, 10);
		long endTime = System.nanoTime();
		System.out.println("getTopKByArray cost: " + (endTime - startTime) + "ns.");

		// by min heap
		startTime = System.nanoTime();
		ta.getTopKByMinHeap(array, 10);
		endTime = System.nanoTime();
		System.out.println("getTopKByMinHeap cost: " + (endTime - startTime) + "ns.");
		
		// by QuickSort
		startTime = System.nanoTime();
		ta.getTopKByQuickSort(array, 10, 0, array.length - 1);
		endTime = System.nanoTime();
		System.out.println("getTopKByQuickSort cost: " + (endTime - startTime) + "ns.");
		/**
		 * summary: from stdout, getTopKByMinHeap is about 10% faster than getTopKByArray.
		 * <br> getTopKByQuickSort is the best in performance.(maybe not, on i7, getTopKByMinHeap cost: 1590048ns.
				getTopKByQuickSort cost: 3158749ns.)
		 */
	}

}
