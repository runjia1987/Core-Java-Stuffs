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
	private Map<String, Object> resourcesMap = new TreeMap<String, Object>();
	
	public Resource(){
		int size = 1 << 10;
		
		// put sth to Map
		int i = 0;
		Random rand = new Random(System.currentTimeMillis());
		while (i++ < size) {
			resourcesMap.put("" + rand.nextInt(1 << 16), "");
		}
	}
	
	public void doSth(){
		Map<String, ?> subMap = ((TreeMap<String, Object>)resourcesMap).tailMap("2000");
		System.out.println("subMap size: " + subMap.size());
	}
	
	public Map<String, Object> getResourcesMap() {
		return resourcesMap;
	}

}
