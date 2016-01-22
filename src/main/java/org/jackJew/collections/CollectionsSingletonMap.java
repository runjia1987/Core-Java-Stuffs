package org.jackJew.collections;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Collections;

/**
 * 仿 Collections.singleton / singletonMap / singletonSet的实现
 * @author runjia
 */
public class CollectionsSingletonMap<K, V> extends AbstractMap<K, V> implements Serializable {
	
	private static final long serialVersionUID = -6303383966812545693L;
	private final K key;
	private final V value;
	
	public CollectionsSingletonMap(K key, V value){
		this.key = key;
		this.value = value;
	}	

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return MyCollections.<Map.Entry<K, V>>singletonSet(new MyMapEntry<K, V>(key, value));
	}
	
	class MyMapEntry<M, N> implements Map.Entry<M, N>, java.io.Serializable {
		private static final long serialVersionUID = -5026635912628993379L;
		private final M key;
		private final N value;
		
		public MyMapEntry(M key, N value){
			this.key = key;
			this.value = value;
		}

		@Override
		public M getKey() {
			return key;
		}

		@Override
		public N getValue() {
			return value;
		}

		@Override
		public N setValue(N value) {
			// Notice: don't allow setValue()
			// @see java.util.AbstractMap.SimpleImmutableEntry<K, V>
			throw new UnsupportedOperationException();
		}
		
	}
	
}

class CollectionsSingletonList<E> extends AbstractList<E> implements Serializable {
	
	private static final long serialVersionUID = 2780072948101989208L;
	private final E element;
	
	public CollectionsSingletonList(E ele){
		this.element = ele;
	}

	@Override
	public E get(int index) {
		if(index == 0)
			return element;
		else throw new IllegalArgumentException("index is illgal, only 0 is allowed!");
	}

	@Override
	public int size() {
		return 1;
	}
	
	
	
}

class CollectionsSingletonSet<E> extends AbstractSet<E> implements Serializable {
	
	private static final long serialVersionUID = 1550887066397505379L;
	private final E element;
	private boolean hasNext;
	
	public CollectionsSingletonSet(E ele){
		this.element = ele;
		hasNext = true;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return hasNext;
			}

			@Override
			public E next() {
				if(hasNext()) {
					hasNext = false;
					return element;
				} else   // Notice: NoSuchElementException used
					throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				// Notice: insure that no remove is allowed for SingletonSet and SingletonMap
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public int size() {
		return 1;
	}
	
}

class MyCollections {
	
	static <K ,V> Map<K, V> singletonMap(K key, V value){
		//Map map = new java.util.HashMap<K ,V>(100);   // passed
		return new CollectionsSingletonMap<K, V>(key, value);
	}
	
	static <E> List<E> singletonList(E element){
		return new CollectionsSingletonList<E>(element);
	}
	
	static <E> Set<E> singletonSet(E element){
		return new CollectionsSingletonSet<E>(element);
	}
	
}

class TestClient99 {
	/**
	 * 测试代码
	 */
	public static void main(String... args){
		Set<String> set = MyCollections.singletonSet("abcd");
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		
		Date date = new Date();
		List<Date> list = MyCollections.singletonList(date);
		System.out.println("list size: " + list.size());
		//list.remove(0);   // unsupported operation
		
		
		Map<String, Date> map = MyCollections.singletonMap("abcd", date);
		Set<Map.Entry<String, Date>> entrySet = map.entrySet();
		Iterator<Map.Entry<String, Date>> itr2 = entrySet.iterator();
		while(itr2.hasNext()) {
			Map.Entry<String, Date> entry = itr2.next();
			System.out.println(entry.getKey() + "," + entry.getValue());
			itr2.remove();	// unsupported operation
		}
	}
	
}