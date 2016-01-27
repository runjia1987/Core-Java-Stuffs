package org.jackJew.AOP.proxyTest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("MethodInvokingFactoryBeanTest")
public class MethodInvokingFactoryBeanTest {
	
	static Logger logger = Logger.getLogger(MethodInvokingFactoryBeanTest.class);
	
	@Resource(name="status")
	private Integer status;
	
	// injected from factoryBean in xml schema
	@Resource(name="osVersion")
	private String osVersion;

	/**
	 * Test
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MethodInvokingFactoryBeanTest bean = context.getBean("MethodInvokingFactoryBeanTest",
													MethodInvokingFactoryBeanTest.class);
		
		logger.info(bean);
		// status: 74, osVersion: Windows 8.1
		((AbstractApplicationContext)context).close();
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
