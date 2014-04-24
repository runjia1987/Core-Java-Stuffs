package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestClient {
	
	public static void main(String... args){
		// 这一句仅仅是为了获得beanFactory
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		SingletonBean2 singletonBean = factory.getBean(SingletonBean2.class);
		singletonBean.setName("单例bean");
		singletonBean.print();
		singletonBean.print();
		
	}

}
