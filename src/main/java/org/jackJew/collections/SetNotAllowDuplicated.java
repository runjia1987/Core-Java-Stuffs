package org.jackJew.collections;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetNotAllowDuplicated {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		Set<String> set = new HashSet<String>();
		set.add("123");
		set.add("123");	//不允许重复元素的存在
		
		String s = "abc";
		String s1 = new String(s);	//不允许重复元素的存在
		String s2 = "def";	// e==null? e2==null: e.equals(e2) 是否在已有的Set中存在e2
		set.add(s);
		set.add(s1);
		set.add(s2);
		set.add(null);
		set.add(null);
		
		System.out.println(Arrays.toString(set.toArray()));
		
	}

}
