package org.jackJew.AOP.transaction.declare.annotation;

import java.util.ArrayList;
import java.util.List;

import org.jackJew.AOP.proxyTest.Interface1;
import org.jackJew.AOP.proxyTest.MyBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 声明式事务管理 (annotation style)
 * 
 * @author Jack
 *
 */
public class TestClient {

	/**
	 * Use aspectj style uses <aop:aspectj-autoproxy /> in XML.
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();

		factory.addBeanPostProcessor(new MyBeanPostProcessor());

    context.getBean(IService.class);

		List<Pojo> list = new ArrayList<Pojo>(2);
		Pojo pojo = new Pojo(1, "runjia", "123456");
		list.add(pojo);
		pojo = new Pojo(2, "abcd", "xxxxxxx");
		list.add(pojo);
		ValidService validService = context.getBean("transactionValidService", ValidService.class);
		validService.insert(list);
		// normally executed, with subPath rollbacked.
		
		((AbstractApplicationContext)context).close();
	}

}
