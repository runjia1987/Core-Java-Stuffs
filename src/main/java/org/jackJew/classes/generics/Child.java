package org.jackJew.classes.generics;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Child <P extends Collection<Parent>, Q, R> extends Parent{
	
	class Superclass <T extends CharSequence> { 
		  public void m1( T arg) {  } 
		  public T m2() { return null;  } 
	}
	
	public <C extends java.util.Map<String,? super Serializable>> void publish(C instance) {
		instance.put("key", "");
	}
	
	class Subclass extends Superclass<String> {

		@Override
		public void m1(String arg) {
			super.m1(arg);
		}

		@Override
		public String m2() {
			return super.m2();
		} 
		// synthetic bridge methods after Type Erasure, will add those two methods in parent class
	} 
	
	//Parent p = new Parent();  //不能实例化类型
	
	public Child() {
		Q[] t = null;
		// ***************** 泛型数组创建 ***************** //
		t = (Q[])new Object[5];	//第一种方式, 由Object类型强制转换
		
		Class<?> cls = String.class;
		t = (Q[])Array.newInstance(cls, 5);	//第二种方式, 反射创建
		// ***************** End ***************** //
	}
	
	public <T> void createMap(){
		HashMap<T, WeakReference<T>> proxies = new HashMap<T, WeakReference<T>>();
		
		Set<T> elements = new HashSet<T>();
	}
	
	public static void main(String[] args){
		
		//List<Date> dataList = new ArrayList<Timestamp>();	//compile error
		//List<? super Date> dataList1 = new ArrayList<Timestamp>();  //compile error
		List<? extends Date> dataList11 = new ArrayList<Timestamp>();
		
		List<?> dataList2 = new ArrayList<Timestamp>();
		//dataList2.add(new Timestamp(0));	//compile error
		List<? super Timestamp> dataList3 = new ArrayList<Timestamp>();  // !注意此语法
		List<? extends Timestamp> dataList4 = new ArrayList<Timestamp>();  // !注意此语法
		dataList3.add(new Timestamp(100000));   	//OK
		//dataList4.add(new Timestamp(1000000));	//Error
	}
	
	static class SubTimeStamp extends Timestamp {

		public SubTimeStamp(long time) {
			super(time);
		}
		
	}
	
	public List<?> getList(){
		List<?> list = new ArrayList<Object>();
		return list;
	}
	
	/**
	 * extends指示上界add方法受限，super指示下界get方法受限
	 */
	
	/**
	 * extends指示类型上界，实际类型可能为Parent或其子类型
	 * <br>
	 * 是否拥有泛型方法，与其所在的类是否泛型没有关系。要定义泛型方法，只需将泛型参数列表置于返回值前
	 */
	public <T extends Parent> T get(List<T> list) {
		
		List<T> lt1 = new ArrayList<T>();
		List<T>[] lt2 = null;
	    
		T p = list.get(0);
		
	    return p;
	}
	
	
	public P get2(List<? extends P> list) {
		
		List<? super P> list2 = new ArrayList<P>();
		List<? extends P> list3 = new ArrayList<P>();  // !注意此语法
		List<? super P> list4 = new ArrayList<Object>();
		
		P ele = (P) new ArrayList<Parent>();	//P extends Collection<Parent>
		//list.add(ele);   //compile error
		
		return list.get(0);		//success
	}
	
	/**
	 * super指示类型下界，实际类型可能为Parent或其父类型，直至Object类
	 * 
	 */
	public void get3(List<? super Collection> list) throws InstantiationException, IllegalAccessException{
		
		list.add(Collection.class.newInstance());
		
		//Collection c = list.get(0);	//compile error
	}
	
	public void wildcard(List<? super Number> list) {
	    list.add(1);
	}  
	
	/**
	 * 泛型工厂方法
	 */
	public static <V> List<V> create() {
        return new ArrayList<V>();
    }
	
	public <Y extends Date>  void upperBound(List<Y> list, Y el)  
	{  
	    list.add(el);
	}
	
	public static void main(int i){ }

	@Override
	int add() {
		return 0;
	}

}
class TestGenerics<S extends Child>{
	
}