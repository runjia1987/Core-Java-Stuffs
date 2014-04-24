package org.jackJew.classes.ParentChildInherit;

/**
 * A reference to a class field(static) causes initialization of only the class
 * or interface that actually declares it, even if it might reference the name
 * of a subclass or a subinterface, or a class that implements an interface.
 */
public class ParentChildInitialize {

	public static void main(String[] args) {
		System.out.println(Child1.x);	//output: 999
	}

}

class Parent1 {
	static int x = 999;
	
	static {
		System.out.println("Parent static initializer block.");
	}
}

class Child1 extends Parent1 {
	
	static {
		System.out.println("Child static initializer block.");	//不被执行
	}
	
}
