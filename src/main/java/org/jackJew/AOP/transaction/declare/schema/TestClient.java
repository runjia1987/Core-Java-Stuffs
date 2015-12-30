package org.jackJew.AOP.transaction.declare.schema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {

	/**
	 * Use schema-based configuration in XML, like <tx-advice> -
	 * <tx:attributes>, <aop:config> - <aop:pointcut> - <aop:advisor>.
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		List<Pojo> list = new ArrayList<Pojo>(2);
		Pojo pojo = new Pojo(1, "runjia", "123456");
		list.add(pojo);
		pojo = new Pojo(2, "abcd", "xxxxxxx");
		list.add(pojo);
		ValidService validService = context.getBean("validService2", ValidService.class);
		validService.insert(list);
		// org.springframework.transaction.UnexpectedRollbackException:
		// Transaction rolled back because it has been marked as rollback-only

		((AbstractApplicationContext)context).close();
	}

}
