package org.jack.hash;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T extends Entry> {
	
	private final HashProvider hashProvider;
	
	private short replicationNumber;
	
	private final SortedMap<Integer, T> circleMap = new TreeMap<Integer, T>();
	
	public ConsistentHash (HashProvider provider, short replicationNumber, List<T> nodes){
		this.hashProvider = provider;
		this.replicationNumber = replicationNumber;
		
		for(T node : nodes) {
			add(node);
		}
	}

	public void add(T node) {
		for(short i = 0; i < replicationNumber; i++) {
			circleMap.put(hashProvider.hash(node.getKey().toString() + i), node);
		}		
	}
	
	public T get(Object key){
		if(circleMap.isEmpty()) {
			return null;
		} else {
			int hash = hashProvider.hash(key);
			if( ! circleMap.containsKey(hash)) {
				// try to get a sub map with bigger or equal keys
				SortedMap<Integer, T> tailMap = circleMap.tailMap(hash);
				if(tailMap != null && ! tailMap.isEmpty()) {
					hash = tailMap.firstKey();
				} else {
					hash = circleMap.firstKey();
				}
			}
			return circleMap.get(hash);
		}
	}

}