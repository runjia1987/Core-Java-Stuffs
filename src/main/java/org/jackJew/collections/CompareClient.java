package org.jackJew.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

/**
 * 比较器测试 
 * @author zhurunjia
 */
public class CompareClient {

	/**
	 * TreeSet测试
	 */
	public static void main(String[] args) {
		Element<String> ele = new Element<String>();
		
		Set<String> set = new TreeSet<String>(new MyComparator());
		set.add("runjia");
		set.add("zhu");
		set.add(null);  // 默认的natural排序下set集合不允许null抛异常, 而自定义的比较器支持null值处理
		set.add("123");
		set.add("中国");
		set.add("中国");
		set.add(null);	//不支持插入重复值null
		set.add("0");

		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}

class MyComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {	//在排序中将null值作为最小
		if(o1 == null){
			if(o2 == null)
				return 0;
			else return -1;
		}
		else if (o2 == null)
			return 1;
		else
			return o1.compareTo(o2);
	}
}

class Element<E> implements Comparable<E> {
	
	public int compareTo(E o) {
		if(this == null){
			if(o == null)
				return 0;
			else return -1;
		}
		else
			return o == null ? 1 : this.compareTo(o);  // ?三元操作符没有短路功能
	}
	
	public static void main(String[] args) {
		int i = 100, j = 101;
		Comparable<Integer> comp_i = (Comparable<Integer>)i;
		
		System.out.println(comp_i.compareTo(j));
		// -1
	}
	
}
