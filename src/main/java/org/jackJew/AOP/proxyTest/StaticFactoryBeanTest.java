package org.jackJew.AOP.proxyTest;

import java.util.Random;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class StaticFactoryBeanTest {

	public static void main(String[] args) {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		Object instance = bf.getBean("staticFactoryBean");
		System.out.println("instance type: " + instance.getClass()
											 + ", value is: " + instance.toString());
		
		Object instance2 = bf.getBean("staticFactoryBean");
		System.out.println(instance == instance2);  // will be decided by the bean scope
													// definition in applicationContext.xml
	}
	
	public static Integer generateNumber(String type){
		if("A".equals(type))
			return new Random().nextInt(100);
		else
			return 0;
	}
	
	public static Integer generateNumber(){
		return new Random().nextInt(100);
	}

}
