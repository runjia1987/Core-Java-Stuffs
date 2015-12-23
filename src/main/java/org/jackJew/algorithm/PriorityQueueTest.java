package org.jackJew.algorithm;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.TreeMap;
import java.util.concurrent.SynchronousQueue;

public class PriorityQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TreeMap<String, Object> treeMap = new TreeMap<>();
		// treeMap.put(null, "a");
		// fail, do not allow null when no comparator is provided
		treeMap.put("1", "a");
		treeMap.put("2", "b");
		treeMap.put("3", "c");
		treeMap.put("4", "d");
		System.out.println(treeMap.ceilingKey("1.5"));

		// default init_capacity is 11.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(999);
		pq.add(100);
		pq.add(2);
		pq.add(56);
		pq.add(4);
		pq.add(-5);
		// add: siftUp, k = size, (k-1) >> 1; while loop, find where greater
		// than/equal to its parent, or is the root
		// add: always maintain the 0-index element as the least by siftUp
		// method.

		Integer ele = 0;
		while ((ele = pq.poll()) != null) {
			// poll: siftDown, k = 0 ; (k+1) << 1,while loop, find where less
			// than/equal to its children, or is the leaf
			// poll: always poll the least element by siftDown method before
			// return
			System.out.println(ele);
		}

	}

}
