package org.jackJew.classes.initialization;

public class ClassInitialization {

	static class A {
		final static String STR = "123";  // final primitive types or String type = constants
		static String STR2 = "123";
		/* with final or without is different, 
		 * => class is initialization without final.
		 * => class not initialized with final.
		 */		
		
		static {
			System.out.println("class A initializer block.");
		}
	}
	
	static class B extends A {
		static {
			System.out.println("class B initializer block.");
		}
	}
	
	public static void main(String[] args) {
		System.out.println(B.STR);
		//System.out.println(B.STR2);
		
		class C {
			final static int x = 0;  //OK
			final static String Y = "";
			//static int Z = 0;  //compile error
			//final static Object M = 0;  //compile error
		}
		C instance;
	}

}
