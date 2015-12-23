package org.jackJew.classes.lambda;

/**
 * functional interface: 只能包含一个抽象方法的接口
 * @author Jack
 *
 */
public interface Interface {
	
	String func2(String param1, int param2);
	
	default void fun() {
		System.out.println("fun");
	}

}
