package org.jackJew.interview;

import java.util.List;
import java.util.Set;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Stack;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.CopyOnWriteArrayList;

public class AddIntoList {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		for (String s : list){  // actually call list.iterator()
			if(s.equals("c")){
				list.add("d");	// ConcurrentModificationException,
								// because modCount++ != expectedModCount.
			}
		}
		
		System.out.println(list.size());
	}

}
