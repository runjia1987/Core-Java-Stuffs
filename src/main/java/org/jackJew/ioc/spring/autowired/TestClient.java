package org.jackJew.ioc.spring.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-mini-beans.xml");
		MiniBean bean = context.getBean(MiniBean.class, new Object[]{});
		
		System.out.println(bean.compute());
		
		((AbstractApplicationContext)context).close();
	}

}
