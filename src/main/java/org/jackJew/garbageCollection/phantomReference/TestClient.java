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
		final ProviderService provider = context.getBean("phantomConnectionProviderService", ProviderService.class);		
		
		provider.getConnection().queryOperation();
		provider.getConnection().queryOperation();
		provider.getConnection().queryOperation();
		
		System.gc();
		Thread.sleep(1000);		
	}

}
