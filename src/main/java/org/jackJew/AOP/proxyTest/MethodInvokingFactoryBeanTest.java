package org.jackJew.AOP.proxyTest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component("MethodInvokingFactoryBeanTest")
public class MethodInvokingFactoryBeanTest {
	
	static Logger logger = Logger.getLogger(MethodInvokingFactoryBeanTest.class);
	
	@Resource(name="status")
	private Integer status;
	
	@Resource(name="osVersion")
	private String osVersion;

	/**
	 * Test
	 */
	public static void main(String[] args) {
		//ConfigurableBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 必须使用ApplciationContext容器(非BeanFactory), 否则字段值不能正常注入
		MethodInvokingFactoryBeanTest bean = factory.getBean("MethodInvokingFactoryBeanTest",
													MethodInvokingFactoryBeanTest.class);
		//try {int i = 100 / 0;}catch(Exception e){logger.fatal(e);}
		//int i = 100 / 0;
		
		logger.info(bean);
	}
	
	@Override
	public String toString(){
		return "status: " + getStatus() + ", osVersion: " + getOsVersion();
	}

	public Integer getStatus() {
		return status;
	}

	public String getOsVersion() {
		return osVersion;
	}

}
