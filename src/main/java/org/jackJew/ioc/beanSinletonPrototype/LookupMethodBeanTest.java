package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class LookupMethodBeanTest {

	/**
	 * <lookup-method name="" bean=""/> 主要是为了实现单例bean依赖于原型bean的问题
	 */
	public static void main(String[] args) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(factory);
		definitionReader.loadBeanDefinitions("classpath:applicationContext.xml");
		
		for(int i = 0; i< 5; i++) {
			// output different instances for each call
			SingletonBean singletonBean = (SingletonBean)factory.getBean("lookupSingletonBean");
			
			System.out.println(singletonBean.getClass());  // SingletonBean$$EnhancerBySpringCGLIB$$xxxx
			System.out.println(singletonBean.getNewPrototypeBean());
		}
		factory.destroySingletons();
	}

}
