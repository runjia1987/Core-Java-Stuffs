package org.jackJew.weirdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoopWhenSublist {
	
	public static void main(String[] args) {
		int capacity = 1 << 10;
		List<String> stringList = new ArrayList<String>(capacity);
		Random rand = new Random();
		for(int i = 0 ; i < capacity; i++) {
			stringList.add("" + rand.nextInt(1 << 12));
		}
		test(stringList);
	}

	public static void test(List<String> stringList) {
		
		int index = 0;
		final int batch_number = 100;
		while(index < stringList.size()) {
			int endIndex = index + batch_number;
			endIndex = endIndex > stringList.size() ? stringList.size() : endIndex;
			System.out.println("index: " + index + ", endIndex: " + endIndex);
			
			List<String> subList = stringList.subList(index, endIndex);
			System.out.println("subList.size(): " + subList.size());
			
			index = endIndex;
		}
	}

}
