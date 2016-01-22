package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

public class LookupMethodBeanTest {

	/**
	 * <lookup-method name="" bean=""/> 主要是为了实现单例bean依赖于原型bean的问题
	 */
	public static void main(String[] args) {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		
		for(int i = 0; i< 5; i++) {
			// output different instances on each call
			SingletonBean singletonBean = (SingletonBean)bf.getBean("lookupSingletonBean");
			
			System.out.println(singletonBean.getClass());  // SingletonBean$$EnhancerBySpringCGLIB$$xxxx
			System.out.println(singletonBean.getNewPrototypeBean());
		}
		((ConfigurableBeanFactory)bf).destroySingletons();
	}

}
