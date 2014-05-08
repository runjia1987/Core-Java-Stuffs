package org.jackJew.algorithm;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class PriorityQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(999);
		pq.add(100);
		pq.add(2);
		pq.add(56);
		pq.add(4);
		pq.add(-5);
		// 2 4 100 999 56
		
		Integer ele = 0;
		while( (ele = pq.poll()) != null ) {
			System.out.println(ele);
		}
		
	}

}
