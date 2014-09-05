package org.jackJew.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * top K algorithm
 * @author Jack
 *
 */
public class TopK_Algorithm {
	
	public int[] createRandomString(int length){
		int[] array = new int[length];
		int i = 0;
		Random rand = new Random();
		while(i < length) {
			array[i++] = rand.nextInt(10000);
		}
		
		return array;
	}
	
	public void shiftArray(int[] array, int startIndex, int endIndex, int length){
		System.arraycopy(array, startIndex, array, endIndex, length);
	}
	
	/**
	 * use k-size array and array shifting to find top k asc
	 * @param array
	 * @param k
	 */
	public void getTopKByArray(int[] array, int k){
		int i = 0, index = 0, max = array.length;
		int[] store =  new int[k];
		System.arraycopy(array, 0, store, 0, k);
		index = k;
		
		System.out.println(Arrays.toString(store));
		// asc sort the store array
		int a, b;
		for (a = 0; a < k - 1; a++) {
			int c1 = store[a], indexB = a;
			for ( b = a+1; b < k; b++) {
				if (store[b] < c1) {
					c1 = store[indexB=b];
				}
			}
			store[indexB] = store[a];
			store[a] = c1;
		}
		System.out.println(Arrays.toString(store));
		
		// iterator over the rest
		while( index < max) {
			i = array[index++];
			if (i > store[0]) {
				// switch and shift array
				
			}
		}
		// print out the store arrays holding top K elements;
		System.out.println(Arrays.toString(store));
	}
	
	/**
	 * use min heap and sift up & down to find top k asc
	 * @param array
	 * @param k
	 */
	public void getTopKByMinHeap(int[] array, int k){
		
	}
	
	/**
	 * @see java.util.PriorityQueue#siftUp
	 * @param i
	 * @param element
	 */
	public void siftUp(int i, int element){
		
	}
	
	/**
	 * @see java.util.PriorityQueue#siftDown
	 * @param i
	 * @param element
	 * @param max
	 */
	public void siftDown(int i, int element, int max){
		
	}
	
	public static void main(String[] args){
		TopK_Algorithm ta = new TopK_Algorithm();
		int[] array = ta.createRandomString(100);
		System.out.println(Arrays.toString(array));
				
		ta.getTopKByArray(array, 10);
	}

}
