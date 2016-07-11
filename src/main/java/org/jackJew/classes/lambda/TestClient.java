package org.jackJew.classes.lambda;

import java.util.Comparator;

public class TestClient {

	public static void main(String[] args) {
		Interface interface1 = (param1, param2) -> {return "123";};
		System.out.println(interface1.defaultMethod("abc", 1000));  // 123
		
		Comparator<Long> cmpr = (long1, long2) -> {return (int) (long1 - long2);};
		int result = cmpr.compare(9999L, 8888L);
		System.out.println(result); // 1111
		
		// 默认的方法不能在lambda表达式内部被访问到
		
	}

}
