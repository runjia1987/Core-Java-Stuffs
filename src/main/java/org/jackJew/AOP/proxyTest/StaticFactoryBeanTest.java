package org.jackJew.AOP.proxyTest;

import java.util.Random;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class StaticFactoryBeanTest {

	public static void main(String[] args) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(factory);
		definitionReader.loadBeanDefinitions("applicationContext.xml");
		
		Object instance = factory.getBean("staticFactoryBean");
		System.out.println("instance type: " + instance.getClass()
											 + ", value is: " + instance.toString());
		// instance type: class java.lang.Integer, value is: xxx
		
		Object instance2 = factory.getBean("staticFactoryBean");
		System.out.println(instance == instance2);  // true, this is a singleton factory-bean
		
		Object sysProps = factory.getBean("sysProps");
		System.out.println("sysProps type: " + sysProps.getClass());
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
