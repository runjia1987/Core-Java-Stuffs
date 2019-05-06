package org.jackJew.AOP.proxyTest;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component("factoryBean")
public class InstanceFactoryBeanTest {
	
	private Date instance4Date = new Date();

	public Date getStr(int type){
		if(type == 999){
			return new Date();
		} else {
			return instance4Date;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(factory);
		
		// use wildcard resource searching
		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		definitionReader.loadBeanDefinitions(
				rpr.getResources("classpath*:applicationContext*.xml"));
		
		// instanceFactoryBean is a factory-bean defined in xml schema
		Object instance = factory.getBean("instanceFactoryBean");
		System.out.println("instance type: " + instance.getClass()
							+ ", value is: " + instance.toString());
		// instance type: class java.util.Date, value is: xxx
		
		Object instance2 = factory.getBean("instanceFactoryBean");
		System.out.println(instance == instance2);  // false, a prototype factory-bean
		
		factory.destroySingletons();
	}

}
