package org.jackJew.annotation.inherited;

import java.lang.reflect.Method;

public class TestClient {
	
	public static void main(String... args) throws ClassNotFoundException, SecurityException, NoSuchMethodException{
		classTest();
		methodTest();
	}
	
	/**
	 * 类继承的注解测试
	 * @throws ClassNotFoundException 
	 */
	private static void classTest() throws ClassNotFoundException{
		//反射获取类/直接声明
		Class<Child> c = Child.class;
		
		if(c.isAnnotationPresent(InheritedAnno.class)){
			InheritedAnno it = c.getAnnotation(InheritedAnno.class);
			System.out.println(it.content());
		}
	}
	
	/**
	 * 方法继承的注解测试
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	private static void methodTest() throws SecurityException, NoSuchMethodException{
		Method m = Child.class.getMethod("print", (Class[])null);
		m.setAccessible(true);
		
		if(m.isAnnotationPresent(InheritedAnno.class)){
			InheritedAnno it = m.getAnnotation(InheritedAnno.class);
			System.out.println(it.content());
		}
	}

}
