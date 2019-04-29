package org.jackJew.garbageCollection.phantomReference;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * ResourceWrapper
 * @author Jack
 *
 */
public class Resource {

	/**
	 * sth to exhaust memory
	 */
	private Map<String, Object> field = new TreeMap<>();
	
	public Resource(){
		int size = 1 << 10;
		
		// put sth to map
		int i = 0;
		Random rand = new Random(System.currentTimeMillis());
		while (i++ < size) {
			field.put("" + rand.nextInt(1 << 16), "");
		}
	}
	
	public void doSth(){
		Map<String, ?> tailMap = ((TreeMap<String, Object>)field).tailMap("2000");
		System.out.println("tailMap size: " + tailMap.size());
	}
	
	public Map<String, Object> getField() {
		return field;
	}

}
