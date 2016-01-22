package org.jackJew.AOP.proxyTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeforeClient {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		Interface1 bean = (Interface1)context.getBean("beforeProxyBean");
		
		bean.print(100, 300);
		
		// 输出: class org.springframework.aop.framework.ProxyFactoryBean
		System.out.println(context.getBean("&beforeProxyBean").getClass());
		System.out.println(context.getResource("file:/备忘.txt").exists());  // false
		
		((AbstractApplicationContext)context).close();
		
	}

}
