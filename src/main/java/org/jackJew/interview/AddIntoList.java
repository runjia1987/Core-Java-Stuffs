package org.jackJew.interview;

import java.util.List;
import java.util.ArrayList;

public class AddIntoList {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		for (String s : list){  // actually call list.iterator()
			list.remove(s);
			System.out.println(s);
		}
		
		System.out.println(list.size());
	}

}
