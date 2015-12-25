package org.jackJew.AOP.proxyTest;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * AutowiredAnnotationBeanPostProcessor, RequiredAnnotationBeanPostProcessor, CommonAnnotationBeanPostProcessor defined internally in Spring
 * @author runjia
 */
public class ContextSingletonBeanFactoryLocatorTest {

	public static void main(String[] args) {
		
		BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance("beanDefRef.xml");
		
		BeanFactoryReference reference = locator.useBeanFactory("appContext_1");
		
		// actually you get the ApplicationContext container
		BeanFactory factory = reference.getFactory();
		System.out.println(factory.getBean("beforeProxyBean"));
		((AbstractApplicationContext)factory).close();
	}

}
