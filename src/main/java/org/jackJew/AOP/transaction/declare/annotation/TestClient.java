package org.jackJew.AOP.transaction.declare.annotation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {

	/**
	 * Use aspectj style uses <aop:aspectj-autoproxy /> in XML.
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		List<Pojo> list = new ArrayList<Pojo>(2);
		Pojo pojo = new Pojo(1, "runjia", "123456");
		list.add(pojo);
		pojo = new Pojo(2, "abcd", "xxxxxxx");
		list.add(pojo);
		context.getBean("transactionValidService", ValidService.class).callService(list);
		
		ConfigurableBeanFactory cbf = (ConfigurableBeanFactory)(context.getAutowireCapableBeanFactory());
		cbf.destroySingletons();
	}

}
