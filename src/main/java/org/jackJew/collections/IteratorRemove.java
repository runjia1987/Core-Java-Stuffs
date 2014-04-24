package org.jackJew.collections;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IteratorRemove {
	
	private static List<String> list = new ArrayList<String>(3);
	public final static ThreadLocal<Date> threadLocal = new ThreadLocal<Date>();

	/**
	 * 
	 */
	public static void main(String[] args) {
		list.add("a");
		list.add("b");
		list.add("c");
		
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()){
			System.out.println("more next...");
			String element = itr.next();
			System.out.println(element);
			if(element.equals("c"))
				itr.remove();
				//list.remove(element);
		}
		
		System.out.println("list.size(): " + list.size());
		System.out.println(Integer.MAX_VALUE);
		
	}

}
