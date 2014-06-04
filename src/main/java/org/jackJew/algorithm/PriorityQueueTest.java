package org.jackJew.algorithm;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.TreeMap;
import java.util.concurrent.SynchronousQueue;

public class PriorityQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// default init_capacity is 11.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(999);
		pq.add(100);
		pq.add(2);
		pq.add(56);
		pq.add(4);
		pq.add(-5);
		// add: siftUp,  k = size,  (k-1) >> 1; while loop, find greater than
		// add: always maintain the 0-index element as the least by siftUp method.
		
		Integer ele = 0;
		while( (ele = pq.poll()) != null ) {
			// poll: siftDown,  k = 0 ; (k+1) << 1,while loop, find less than
			// poll: always poll the least element by siftDown method before return
			System.out.println(ele);
		}
		
	}

}