package org.jackJew.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionModifyField {

	public static void main(String[] args) throws Exception {
		Class<?> cls = Class.forName("org.jackJew.reflection.Pojo");
		
		//取全部public, private, protected, default修饰的成员字段
		Field[] fields = cls.getDeclaredFields();
		//构造一个实例
		Object instance = cls.newInstance();
		
		// like Spring 3.x org.springframework.beans.factory.support.SimpleInstantiationStrategy
		Constructor<?> ctor = cls.getDeclaredConstructor((Class<?>[])null);
		Object instance2 = ctor.newInstance((Object[])null);
		System.out.println("instance equals instance2: " + (instance.equals(instance2)));
		
		for(Field f : fields){
			f.setAccessible(true);
			if(f.getName() == "id")		//根据成员名称进行赋值
				f.setInt(instance, 99);
			else if(f.getName() == "name")
				f.set(instance, "runjia");
		}
		
		Pojo pojo = (Pojo)instance;
		System.out.println( pojo.getId() + "\n" + pojo.getName());
	}

}
class Pojo {
	
	private int id;
	private String name;	
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
