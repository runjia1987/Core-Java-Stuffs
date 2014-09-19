package org.jackJew.garbageCollection.phantomReference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * test case
 * @author Jack
 *
 */
public class TestClient {
	
	public static void main(String[] args) throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService provider = context.getBean("phantomConnectionProviderService", ProviderService.class);		
		
		int i = 0;
		while (i++ < 10) {
			PhantomConnection pCon = provider.getConnection();
			pCon.queryOperation();
			
			Thread.sleep(1000);
		}
	}

}
