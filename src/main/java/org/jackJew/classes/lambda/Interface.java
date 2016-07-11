package org.jackJew.classes.lambda;

/**
 * functional interface
 * @author Jack
 *
 */
public interface Interface {
	
	String normal(String param1, int param2);
	
	default String defaultMethod(String param1, int param2) {
		return normal(param1, param2);
	}
	
	default String defaultMethod2(String param1, int param2) {
		return "defaultMethod2";
	}
	
	static void func() {
		System.out.println("func");
	}

}
