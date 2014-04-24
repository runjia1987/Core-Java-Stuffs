package org.jackJew.classes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Stack;

public class ReferenceTest {

	/**
	 * 对象的引用测试
	 */
	public static void main(String[] args) {
		testObject();
		
		testArray();
	}
	
	static void testObject(){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = c1;
		System.out.println(c2.get(Calendar.DAY_OF_WEEK));
		
		//c1 = Calendar.getInstance();	//新建对象, 本质上改变引用指向
		c1.add(Calendar.DAY_OF_MONTH, 2);
		System.out.println(c2.get(Calendar.DAY_OF_WEEK));
		//注意变量引用指向的对象
	}
	
	static void testArray(){		
		String[] array = {"a", "b", "c"};
		String[] temp = array;
		//array = {"a", "b", "c", ""};	//compile error, 直接字面量赋值只能在初始化时使用
		
		array = new String[] {"123", "def"};	//新建对象, 改变引用
		System.out.println("temp: " + Arrays.toString(temp));
		System.out.println("new array: " + Arrays.toString(array));
	}

}
