package org.jackJew.AOP.proxyTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeforeClient {
	
	public static void main(String[] args) {
		
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		Interface1 bean = (Interface1)factory.getBean("beforeProxyBean");
		
		bean.print(100, 300);
		
		// 输出: class org.springframework.aop.framework.ProxyFactoryBean
		System.out.println(factory.getBean("&beforeProxyBean").getClass());
		
		ApplicationContext context = (ApplicationContext)factory;
		System.out.println(context.getResource("file:/备忘.txt").exists());  // false
		
	}

}
