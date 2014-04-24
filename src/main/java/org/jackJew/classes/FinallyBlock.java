package org.jackJew.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FinallyBlock {

	public static void main(String[] args) {
		Object ret = testReference();
		
		System.out.println(":::::::::: try-catch-finally 测试结果:");
		if(ret instanceof String)
			System.out.println(ret);
		
		else if(ret instanceof StringBuilder)
			System.out.println(ret.toString());
		
		else if(ret instanceof List)
			System.out.println( Arrays.toString(((List<?>)ret).toArray()) );//注意泛型通配符
		
		else if(ret instanceof ThreadLocal<?>)
			System.out.println( ((ThreadLocal<?>)ret).get() );  //注意泛型通配符
	}
	
	/**
	 * 测试try...finally块的执行流程(引用类型)
	 * @throws Exception 
	 */
	public static Object testReference() {
		String str = "string123";
		ThreadLocal<String> threadLocal = new ThreadLocal<String>();
		List<Integer> list = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		
		try {
			threadLocal.set("runjia");
			list.add(99999);
			sb.append("ABC");
			//int i = 1 / 0;
			//throw new RuntimeException("强制抛出一个异常 ");
			//return threadLocal;
			//return list;
			return list;	//保存的中间结果只是引用, 因而finally中的操作会影响指向的对象实际值
			
		} catch(Exception e){
			System.out.println(e.getMessage());
			return "exception";	//在return前保存结果, 转finally块处理,执行完毕后return 
		} finally {
			//可以将finally块的操作看作是对final变量的操作,凡final不允许的操作在finally中无效,
			//不影响try流程的返回结果.
			str = "新字串";
			threadLocal.set("hero");
			list.add(10000);
			sb.append("DEF");
			//sb = new StringBuilder("jiajia");sb.append("DEF");
			
			System.out.println("finally块 str:" + str);
			System.out.println("finally块 threadLocal get():" + threadLocal.get());
			System.out.println("finally块 list.size(): " + list.size());
			System.out.println("finally块 sb.toString(): " + sb.toString());
			//return "finally";	 //优先级最高, 优先于try和catch的返回
		}
	}
	
}
