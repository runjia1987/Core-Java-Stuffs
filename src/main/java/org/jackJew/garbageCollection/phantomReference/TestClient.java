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
		
		int i = 0;
		while (i++ < 10) {
			// likewise in real occasions, multi - threads simulation.
			
			Thread t = new Thread(){
				public void run(){					
					try {
						Resource resourceW = provider.getResource();
						resourceW.doSth();
						resourceW = null;  // help GC, this is useful
						
						System.gc();
						
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			};
			t.start();
		}
		
	}

}
