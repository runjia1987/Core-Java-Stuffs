package org.jackJew.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * the top hotest K elements from a huge set of records
 * @author Jack
 *
 */
public class TopHotK_Algorithm {
	
	private static final int maxElementsSize = 1000;
	
	public final String ASCIIWord = "abcdefghijklmnopqrstuvwxyz";
	
	private String[] array;
	
	private final int reduceTask_thread_number = 10;
	
	private final ReduceTask[] reduceTasks = new ReduceTask[reduceTask_thread_number];

	/**
	 * simulate the map, distribution
	 * @author Jack
	 *
	 */
	class MapTask implements Runnable {

		@Override
		public void run() {
			// hash(md5) to map to different task nodes
			String element;
			int hashCode, mod;
			String md5;
			for(int i = 0; i < array.length; i++){
				element = array[i];
				md5 = Md5Util.getMD5(element);
				hashCode = md5.hashCode();
				
				// get mod
				mod = hashCode % reduceTask_thread_number;
				reduceTasks[mod].addElement(element);				
			}
		}
		
	}
	
	/**
	 * find the top K in this range. use org.jackJew.algorithm.TopK_Algorithm implementation.
	 * @author Jack
	 *
	 */
	class ReduceTask implements Runnable {
		
		private List<String> rangeArray = new ArrayList<String>(maxElementsSize / reduceTask_thread_number);

		@Override
		public void run() {
			// TODO
		}
		
		public void addElement(String element){
			this.rangeArray.add(element);
		}	
	}
	
	/**
	 * merge all top K results generated by ReduceTask nodes
	 * @author Jack
	 *
	 */
	class ReduceMergeTask implements Runnable {

		@Override
		public void run() {
			// TODO
		}
		
	}
	
	/**
	 * testcase
	 * @param args
	 */
	public static void main(String[] args){
		TopHotK_Algorithm tha = new TopHotK_Algorithm();
		tha.setArray(tha.createRandomStrings(maxElementsSize));
		
		tha.findDuplicated(tha.getArray());
		
	}
	
	private void findDuplicated(String[] array){
		Map<String, Integer> map = new HashMap<String, Integer>(array.length);
		for (int i = 0; i < array.length; i++) {
			String s = array[i];
			if (map.containsKey(s)) {
				int count = map.get(s);
				map.put(s, ++count);
			} else {
				map.put(s, 1);
			}
		}
		
		Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
		while ( itr.hasNext() ) {
			Map.Entry<String, Integer> entry = itr.next();
			if ( entry.getValue() > 1) {
				System.out.println(entry.getKey());
			}
		}
	}
	
	/**
	 * generate random strings, some of them are identical
	 * @param n the number
	 * @return
	 */
	public String[] createRandomStrings(int n){
		Random rand = new Random();
		int i = 0, length = 3;
		String[] array = new String[n];
		StringBuilder builder = new StringBuilder(length);
		final int sourceLength = ASCIIWord.length();
		
		while( i < n) {
			int j = 0;
			builder.delete(0, length);
			while(j++ < length) {				
				builder.append(ASCIIWord.charAt(rand.nextInt(sourceLength)));
			}
			array[i] = builder.toString();
			i++;
		}
		
		return array;
	}

	private void setArray(String[] array) {
		this.array = array;
	}

	public String[] getArray() {
		return array;
	}

}
