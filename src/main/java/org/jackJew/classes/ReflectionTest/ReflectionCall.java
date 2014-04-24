package org.jackJew.classes.ReflectionTest;

import java.lang.reflect.Method;

public class ReflectionCall {

	/**
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		
		Class<?> cls = Class.forName("org.jackJew.classes.ReflectionTest.Bean");
		Object instance = cls.newInstance();
		int i = 0;
		Method m1 = cls.getDeclaredMethod("setName1", new Class<?>[] {java.lang.String.class } );
		Method m2 = cls.getDeclaredMethod("setName2", new Class<?>[] {java.lang.String.class } );
				
		while ( i++ < 1000000) {
			m1.invoke(instance, "123");
			m2.invoke(instance, "abc");
		}
		
		System.out.println(instance.toString());
		System.out.println((System.currentTimeMillis() - startTime) + " ms.");
	}

}
