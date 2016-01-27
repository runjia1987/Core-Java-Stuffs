package org.jackJew.ioc.spring;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassPathScanningCandidateComponentProvider, ClassPathBeanDefinitionScanner
 * for annotation-based beans scanning;
 * <br/>
 * XmlBeanDefinitionReader for XML Schema-based beans;
 * PropertiesBeanDefinitionReader for Map/Properties (key=value) configuration;
 * @author Jack
 *
 */
@Service("iocFieldPopulation")
public class IocFieldPopulation {
	
	/**
	 * handled by CommonAnnotationBeanPostProcessor
	 */
	@Resource(name="service1")
	private Service1 service1;
	
	@Autowired
	private Service2 service2;
	
	@Value("${jdbc.username}")
	private String jdbc_userName;

	public static void main(String[] args) {		
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		definitionReader.loadBeanDefinitions("classpath:tinyContext.xml");
		
		PropertyPlaceholderConfigurer ppc = beanFactory.getBean("placeholderConfig",
					PropertyPlaceholderConfigurer.class);
		ppc.postProcessBeanFactory(beanFactory);  // call PropertyPlaceHolderConfigurer
		
		// handle @Resource, @PostConstruct, @PreDestroy
		CommonAnnotationBeanPostProcessor beanPostProcessor = new CommonAnnotationBeanPostProcessor();
		beanPostProcessor.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(beanPostProcessor);
		
		// handle @Autowired, @Value, @Inject
		AutowiredAnnotationBeanPostProcessor beanPostProcessor2 = new AutowiredAnnotationBeanPostProcessor();
		beanPostProcessor2.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(beanPostProcessor2);
		
		IocFieldPopulation bean = beanFactory.getBean("iocFieldPopulation", IocFieldPopulation.class);
		System.out.println(bean.getClass());  		// class IocFieldPopulation
		System.out.println(bean.service1);  		//autowiring success
		System.out.println(bean.service2);  		//autowiring success
		System.out.println(bean.jdbc_userName);   	//autowiring success
		
		beanFactory.destroySingletons();		
	}
}

@Service("service1")
class Service1 {
	
	@Resource(name="dataDao1")
	private DataDao1 dataDao1;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(String container){
		System.out.println("org.jackJew.ioc.spring.service1.save(String): " + container);
	}
	
}

@Repository("dataDao1")
class DataDao1 {
	
	public DataDao1() { }
	
}
