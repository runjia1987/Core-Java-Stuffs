package org.jackJew.classes.lambda;

/**
 * functional interface
 * @author Jack
 *
 */
public interface Interface {
	
	String func2(String param1, int param2);
	
	default void fun() {
		System.out.println("fun");
	}

}
