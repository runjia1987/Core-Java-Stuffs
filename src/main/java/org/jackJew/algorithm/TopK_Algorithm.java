package org.jackJew.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * top K algorithm
 * @author Jack
 *
 */
public class TopK_Algorithm {
	
	/**
	 * create a random string array
	 * @param n length
	 * @return
	 */
	public int[] createRandomString(int n){
		int[] array = new int[n];
		int i = 0;
		Random rand = new Random();
		while(i < n) {
			array[i++] = rand.nextInt(10000);
		}
		
		return array;
	}
	
	public void shiftArray(int[] array, int srcIndex, int destIndex, int length){
		System.arraycopy(array, srcIndex, array, destIndex, length);
	}
	
	/**
	 * use k-size array and array shifting to find top k asc
	 * @param array
	 * @param k
	 */
	public void getTopKByArray(int[] array, int k){
		int element = 0, index = 0, max = array.length;
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
			element = array[index++];
			if (element > store[0]) {
				// replace first node and shift array
				// find the index
				int j = 1;
				while(j < k) {
					if ( store[j++] > element) {j--;break;}
				}
				j--;
				shiftArray(store, 1, 0, j);
				store[j] = element;
			}
		}
		// print the store arrays holding top K elements;
		System.out.println(Arrays.toString(store));
	}
	
	/**
	 * use min heap and sift up & down to find top k asc
	 * <br>
	 * time cost: n*logK
	 */
	public void getTopKByMinHeap(int[] array, int k){
		int i = 0, index = 0, max = array.length;
		int[] store = new int[k];
		// set up k-size array
		while( i < k){
			if ( i == 0 ) {
				store[0] = array[index];
			} else {
				siftUp(store, i, array[index]);
			}
			i++;
			index++;
		}
		
		System.out.println(Arrays.toString(store));
		
		while (index < max) {
			int element = array[index++];
			if ( element > store[0]) {
				siftDown(store, 0, element);
			}
		}
		System.out.println(Arrays.toString(store));
	}
	
	/**
	 * @see java.util.PriorityQueue#siftUp
	 * @param i
	 * @param element
	 */
	public void siftUp(int[] array, int i, int element){
		int parent = 0;
		while(i > 0) {
			parent = i >>> 1;
			if (array[parent] <= element) {
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
	public void siftDown(int[] array, int i, int element){
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
			if ( element <= childElement) {
				break;
			}
			array[i] = childElement;
			i = child1;
		}
		array[i] = element;
	}
	
	public static void main(String[] args){
		TopK_Algorithm ta = new TopK_Algorithm();
		int[] array = ta.createRandomString(100);
		System.out.println(Arrays.toString(array));
		
		// by array
		ta.getTopKByArray(array, 10);
		
		// by min heap
		ta.getTopKByMinHeap(array, 10);
	}

}