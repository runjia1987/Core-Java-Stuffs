package org.jackJew.classes.ReflectionTest;

public class NormalCall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 	 long startTime = System.currentTimeMillis();
		 Bean instance = new Bean();
		 
		 int i = 0;
		 while(i++ < 1000000) {
			 instance.setName1("123");
			 instance.setName2("abc");
		 }
		 
		 System.out.println(instance.toString());
		 
		 System.out.println((System.currentTimeMillis() - startTime) + " ms.");
	}

}
