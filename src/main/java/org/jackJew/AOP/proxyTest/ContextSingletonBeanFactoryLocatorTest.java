package org.jackJew.AOP.proxyTest;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 * AutowiredAnnotationBeanPostProcessor, RequiredAnnotationBeanPostProcessor, CommonAnnotationBeanPostProcessor defined internally in Spring
 * @author runjia
 */
public class ContextSingletonBeanFactoryLocatorTest {

	public static void main(String[] args) {
		
		BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance("beanDefRef.xml");
		
		BeanFactoryReference reference = locator.useBeanFactory("sysApplicationContext");
		
		// actually you get the ApplicationContext container
		BeanFactory factory = reference.getFactory();
		
		// ProxyFactoryBean is a FactoryBean, so automatically invoke the getObject() method,
		// which will cause the interceptors and advisors(defined in interceptorNames) to be executed.
		
		// <br> It is handled in ProxyFactoryBean#initializeAdvisorChain() method,
		// <br> iterate on the interceptorNames, get the advice instances, and addAdvisorOnChainCreation.
		// <br> AOP implementation class "JdkDynamicAopProxy" which implements InvocationHandler interface,
		// <br> will automatically call the below method:
			// invoke(Object proxy, Method method, Object[] args) {
			//     List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
		    //	   new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain):
		    //	   recursively call proceed() method on ReflectiveMethodInvocation:
			//	   if(dm.methodMatcher.matches(method, class)) { dm.methodInterceptor.invoke(..); }
			//     ... iterate on the interceptors and advices, invoke them.
			// }
		System.out.println(factory.getBean("beforeProxyBean"));
		
	}

}
