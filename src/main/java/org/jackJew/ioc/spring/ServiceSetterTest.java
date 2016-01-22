package org.jackJew.ioc.spring;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassPathScanningCandidateComponentProvider, ClassPathBeanDefinitionScanner for annotation-based beans;
 * <br/>
 * XmlBeanDefinitionReader for XML Schema-based beans;
 * @author Jack
 *
 */
@Service("testService1")
public class ServiceSetterTest {
	
	/**
	 * CommonAnnotationBeanPostProcessor
	 */
	@Resource(name="service1")
	private Service1 service1;
	
	@Resource(name="service2")
	private Service2 service2;

	public static void main(String[] args) {
		// we have to use ApplciationContext to put @Resource into effects, not BeanFactory.
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("myApplicationContext.xml"));
		
		ServiceSetterTest bean = beanFactory.getBean("testService1", ServiceSetterTest.class);
		System.out.println(bean);
		System.out.println(bean.getClass());  // class org.jackJew.ioc.spring.ServiceSetterTest
		System.out.println(bean.getService1());  //null
		System.out.println(bean.getService2());  //null
		
		
		System.out.println(beanFactory.getBean("service1")); //object
		System.out.println(beanFactory.getBean("service2")); //object
		
		((ConfigurableBeanFactory)beanFactory).destroySingletons();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:myApplicationContext.xml");
		ServiceSetterTest bean2 = context.getBean("testService1", ServiceSetterTest.class);
		System.out.println(bean2);
		System.out.println(bean2.getClass()); // class org.jackJew.ioc.spring.ServiceSetterTest
		System.out.println(bean2.getService1());  // Service1@21fd5faa
		System.out.println(bean2.getService2());  // Service2@47c4ecdc
		
		
		((AbstractApplicationContext)context).close();	
	}

	public Service1 getService1() {
		return service1;
	}
	
	public Service2 getService2() {
		return service2;
	}
}

@Service("service1")
class Service1 {
	
	@Resource(name="dao1")
	private DataDao1 dao1;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(String container){
		System.out.println("org.jackJew.ioc.spring.service1.save(String): " + container);
	}

	public DataDao1 getDao1() {
		return dao1;
	}

	public void setDao1(DataDao1 dao1) {
		this.dao1 = dao1;
	}
	
}

@Repository("dao1")
class DataDao1 {
	
	public DataDao1() { }
	
}
