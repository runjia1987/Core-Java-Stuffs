package org.jackJew.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionUnsupportedOperation {

	/**
	 * 对不可变集合的操作(即会导致底层数据结构size变化)造成的 UnsupportedOperationException
	 */
	public static void main(String[] args) {
		
		String[] array = { "a", "b", "c", "d" };
		
		List<String> stringList = Arrays.asList(array);	 //不可变
		stringList.clear();
		stringList.remove("a");	 //throw java.lang.UnsupportedOperationException
		
		List<String> unmodifiableList = Collections.unmodifiableList(stringList);	//不可变	
		unmodifiableList.add("e");	//throw java.lang.UnsupportedOperationException
		
		List<String> modifiableList = new LinkedList<String>(stringList);
		modifiableList.add("f");	//正常操作
		
	}

}
