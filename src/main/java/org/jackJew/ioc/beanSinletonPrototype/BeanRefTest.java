package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class BeanRefTest {
	/**
	 * 
	 */
	public static void main(String[] args) {
		BeanFactory cbf = new XmlBeanFactory(
				new FileSystemResource("./src/main/resources/applicationContext.xml"));
		Bean1 bean1 = (Bean1)cbf.getBean("bean1");
		System.out.println(bean1.getBean2());
		System.out.println(Boolean.valueOf("false"));
		((ConfigurableBeanFactory)cbf).destroySingletons();
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
