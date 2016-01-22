package org.jackJew.AOP.transaction.programmatic;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 编程式事务管理 (programming style)
 * 
 * @author Jack
 *
 */
public class TestClient {

	public static void main(String... args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		TransactionService service = context.getBean(TransactionService.class);
		service.exec();

		((AbstractApplicationContext)context).close();
	}

}
