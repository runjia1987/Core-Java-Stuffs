package org.jackJew.collections;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.ThreadLocal;
import java.util.Stack;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description: 自定义HashMap实现 
 * @author zhurunjia
 *
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

	final static int SIZE = 997;
	
	@SuppressWarnings("unchecked")
	List<Map.Entry<K, V>>[] buckets = new LinkedList[SIZE];	//LinkedList的底层数据结构是双向链表
	
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K,V>>();
		for(List<Map.Entry<K, V>> bucket: buckets){
			if(bucket == null)	//list空
				continue;
			for(Map.Entry<K, V> entry: bucket){
				set.add(entry);
			}
		}
		
		return set;
	}
	
	public V put(final K key, final V value){
		V oldValue = null;
		int index = key.hashCode() % SIZE;	//hash码取模作为数组下标, 不同的键可以生成相同的索引
		
		if(buckets[index] == null)	//数组的该索引位置为空, 说明尚未有元素被hash至此处
			buckets[index] = new LinkedList<Map.Entry<K,V>>();
		
		List<Map.Entry<K, V>> bucket = buckets[index];	//取到对应的bucket, 链地址法
		
		Map.Entry<K, V> pair = new Map.Entry<K, V>() {
			public K getKey() {
				return key;
			}
			public V getValue() {
				return value;
			}
			public V setValue(V newValue) {
				return value;
			}
		};
		
		boolean found = false;
		for(Map.Entry<K, V> entry: bucket){
			if(entry.getKey().equals(key)){	 //key相同, 覆盖value
				found = true;
				oldValue = entry.getValue();	//旧值引用
				entry.setValue(value);	//新值替换旧值
				break;
			}
		}
		
		if(!found) bucket.add(pair);	//添加至当前list的尾部
		
		return oldValue;	//返回旧值引用或者null
	}
	
	public V get(Object key){
		V value = null;
		int index = key.hashCode() / SIZE;	//计算hash码用作索引，实现快速定位到bucket
		
		if(buckets[index] == null) return null;
		
		for(Map.Entry<K, V> entry: buckets[index]){
			if(entry.getKey().equals(key)){	//遍历找到key对应的value
				value = entry.getValue();
				break;
			}
		}
		
		return value;
	}

}