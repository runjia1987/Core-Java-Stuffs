package org.jackJew.classes.initialization;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * [单个类实例化的执行顺序]<br>
 * 				静态变量 -> 静态初始化块 -> 实例变量-> 普通初始化块 -> 构造器
 * @author zhurunjia
 */
public class StaticIntializationClass {
	
	private static String instance = getJob();   //静态变量
	private Date date = getDate();	 //实例变量
	
	/**
	 * 静态初始块仅执行一次; 可以有多个static initializer blocks, 按顺序执行;
	 */
	static {
		System.out.println("静态初始块 ");
		//new StaticIntializationClass();
	}
	
	{
		System.out.println("普通初始化块");  //普通初始化块, 每次调用构造器都会被执行
	}
	
	private static String getJob(){
		System.out.println("getJob()");
		Object obj = new StaticIntializationClass();
		return "static method job";
	}
	
	public static void staticCall(){
		System.out.println("staticCall()");
	}
	
	private Date getDate() {
		System.out.println("getDate() called.");
		return Calendar.getInstance().getTime();
	}

	public StaticIntializationClass(){
		System.out.println("default constructor");	//默认构造器		
	}
	
	public static void main(String[] args) {
		StaticClass22 instance = null;  // will not initialize class.
		// StaticClass22.MyMap.put("123", "abc");
		
		//System.out.println(StaticClass22.MyMap);
		System.out.println(StaticClass22.ABC);
	}
}

class StaticClass22 {
	
	// We call a variable, of primitive type or type String, that is final and initialized 
	// with a compile-time constant expression (§15.28) a constant variable.
	// Java Spec: http://docs.oracle.com/javase/specs/jls/se5.0/html/typesValues.html#10931
	final static String ABC = "ABC";
	
	// this is not a constant variable, so will cause class initialization !
	final static Map<String, String> MyMap = new HashMap<String, String>(16);
	
	static {
		System.out.println("StaticClass22 static initializer block.");
	}
	
}
