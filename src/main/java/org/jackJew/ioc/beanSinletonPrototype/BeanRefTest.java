package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;

public class BeanRefTest {
	/**
	 * 
	 */
	public static void main(String[] args) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(factory);
		definitionReader.loadBeanDefinitions(
				new FileSystemResource("./src/main/resources/applicationContext.xml"));		
		
		Bean1 bean1 = (Bean1)factory.getBean("bean1");
		System.out.println(bean1.getBean2());
		factory.destroySingletons();
	}

}

class Bean1 {
	private Bean2 bean2;

	public Bean2 getBean2() {
		return bean2;
	}

	public void setBean2(Bean2 bean2) {
		this.bean2 = bean2;
	}
	
}
class Bean2 {
	
}
