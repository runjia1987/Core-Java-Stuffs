package org.jackJew.classes.lambda;

import java.util.Comparator;

public class TestClient {

	public static void main(String[] args) {
		Interface interface1 = (param1, param2) -> {return "123";};
		interface1.fun();
		
		Comparator<Long> cmpr = (long1, long2) -> {return (int) (long1 - long2);};
		int result = cmpr.compare(9999L, 8888L);
		System.out.println(result);
	}

}
