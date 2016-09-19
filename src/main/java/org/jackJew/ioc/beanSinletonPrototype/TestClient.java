package org.jackJew.ioc.beanSinletonPrototype;

import org.jackJew.classes.initialization.AbstractParent;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestClient {
	
	public static void main(String... args){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		SingletonBean singletonBean = context.getBean(SingletonBean.class);
		System.out.println("singletonBean class： " + singletonBean.getClass());
		// org.jackJew.ioc.beanSinletonPrototype.SingletonBean$$EnhancerByCGLIB$$e95752e6
		// @see org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate
		// <br> and CglibSubClassingInstantiationStrategy
		
		singletonBean.setName("单例bean");
		singletonBean.print();
		singletonBean.print();
		
		AbstractParent parent = context.getBean(AbstractParent.class);
		System.out.println("parent: " + parent);
		
		((AbstractApplicationContext)context).close();
	}

}
