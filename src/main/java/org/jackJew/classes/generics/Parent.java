package org.jackJew.classes.generics;

import java.util.Collection;

class Compare implements Comparable<String> {

	@Override
	public int compareTo(String o) {
		return 0;
	}
	
	/**
	 * 类型擦除时生成如下bridge method:
	 * public bridge synthetic int compareTo(java.lang.Object arg0);
	 */	
}

public abstract class Parent<T extends Collection<String>> {
	
	public Parent(){
		//C c = new C();	//compile error, 位置放在前面了
		
		class C {	//在方法内定义类, 类的作用域被限制
			private int i = 0;
		}
		
		C c = new C();	//right
	}
	
	abstract int add();
	
	public T getT(){
		return null;
	}
	
	public static void main(String[] args){		
		System.out.println(Integer.MAX_VALUE);
		//List<String, Integer> list = null;
		System.out.println("123".hashCode());
		
		class C {
			private int i = 0;
		}		
	}
	
	//public abstract <T extends Child> void method(T p);

}