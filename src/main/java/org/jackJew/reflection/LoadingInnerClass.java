package org.jackJew.reflection;

import java.lang.reflect.Constructor;

public class LoadingInnerClass {

	/**
	 * loading a non-static inner class, use Class.forName and Constructor.newInstance
	 */
	public static void main(String[] args) throws Exception, IllegalAccessException {
		Class<?> cls1 = Class.forName("org.jackJew.classes.innerclass.OuterClass");
		System.out.println(cls1);
		Object instance1 = cls1.newInstance();
		System.out.println(instance1 + "\n");
		
		Class<?> innerCls = Class.forName("org.jackJew.classes.innerclass.OuterClass$InnerClass");
		System.out.println(innerCls);
		
		//innerCls.newInstance();		// throw java.lang.InstantiationException
		Constructor ctor1 = innerCls.getConstructor(new Class<?>[]{ cls1 } );
		System.out.println(ctor1);		
		ctor1.setAccessible(true);  	// !!! the constructor must be public and setAccessible
		
		Object instance2 = ctor1.newInstance(instance1);
		System.out.println(instance2);
	}

}
