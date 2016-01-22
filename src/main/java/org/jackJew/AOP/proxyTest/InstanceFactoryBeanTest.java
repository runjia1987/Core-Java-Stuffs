package org.jackJew.AOP.proxyTest;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component("factoryBean")
public class InstanceFactoryBeanTest {
	
	private Date instance4Date = new Date();

	public Date getStr(int type){
		//return instance4Date;
		return new Date();
	}
	
	public static void main(String[] args) {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		Object instance = bf.getBean("instanceFactoryBean");
		System.out.println("instance type: " + instance.getClass()
											 + ", value is: " + instance.toString());
		
		Object instance2 = bf.getBean("instanceFactoryBean");
		System.out.println(instance == instance2);
	}

}
