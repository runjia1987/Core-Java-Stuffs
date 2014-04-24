package org.jackJew.AOP.proxyTest;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryPropertyPlaceHolderConfigurerTest {
	
	static Logger logger = Logger.getLogger(BeanFactoryPropertyPlaceHolderConfigurerTest.class);

	/**
	 * test for PropertyPlaceHolderConfigurer
	 */
	public static void main(String[] args) {
		//ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("jdbc.properties"));
		ppc.postProcessBeanFactory(factory);
		
		
		DataSource ds = (DataSource) factory.getBean("dataSource");
		logger.info(ds);
		
		logger.info(factory.getBean("propertiesProviderBean"));  // factoryBean: Properties.toString()
	}

}
