package org.jackJew.bugs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * show a bug that parent class calls overriden methods by child class,
 * <br> a very simple bug, but somebody usually do this.
 * @author Jack
 *
 */
public class MyHashMap extends ConcurrentHashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public Object get(Object key) {
		
		// in JDK 5,6,7, this code is just fine;
		// but in JDK 8, super.containsKey directly invoke get(key) which is overriden by 
		// child class, recursive invocation happens, causing a serious error: StackOverflowError
		if(super.containsKey(key)) {
			return new Object();
		}
		return new Object();
	}
	
	/**
	 * please run in JDK 6 and JDK 8, to get the error.
	 * @param args
	 */
	public static void main(String[] args){
		Map map = new MyHashMap();
		map.put("123", new Object());
		
		map.get("123");
	}

}