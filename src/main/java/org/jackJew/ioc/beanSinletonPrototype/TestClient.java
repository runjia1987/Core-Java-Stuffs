package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestClient {
	
	public static void main(String... args){
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		SingletonBean singletonBean = factory.getBean(SingletonBean.class);
		System.out.println("singletonBean class： " + singletonBean.getClass());
		// org.jackJew.ioc.beanSinletonPrototype.SingletonBean$$EnhancerByCGLIB$$e95752e6
		// @see org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate
		// <br> and CglibSubClassingInstantiationStrategy
		
		singletonBean.setName("单例bean");
		singletonBean.print();
		singletonBean.print();
		
	}

}
