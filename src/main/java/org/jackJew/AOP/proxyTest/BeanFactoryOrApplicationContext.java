package org.jackJew.AOP.proxyTest;

import javax.annotation.Resource;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

public class BeanFactoryOrApplicationContext {

	/**
	 * BeanFactory / ApplicationContext differences: <br>
	 * ApplicationContext will automatically instantiate and initialize all non-lazy-init
	 * singleton beans defined when the container is initialized, <br>
	 * while BeanFactory only instantiate and initialize the beans when invoke getBean(name);
	 * <br> ApplcationContext will automatically register the BeanPostProcessors
	 * and BeanFactoryPostProcessors, but BeanFactory will not;
	 * <br> so Spring recommends using the ApplicationContext whenever possible.
	 * <br> ApplicationContext接口继承了 MessageSource (i18n), ResourceLoader,
	 * 								   ApplicationEventPublisher 接口.
	 */
	public static void main(String[] args) {
		StopWatch stop = new StopWatch();
		stop.start();
		
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		//必须使用ApplicationContext容器, 而非BeanFactory, 否则@Resource, @Value不能正常注入
		//ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		factory.addBeanPostProcessor(new MyBeanPostProcessor());
		BeanFactoryPostProcessor bfp = new MyBeanFactoryPostProcessor();
		bfp.postProcessBeanFactory(factory);
		
		MyBean2 bean2 = factory.getBean("MyBean2", MyBean2.class);		//prototype
		System.out.println(bean2.getProp1() + ", " + bean2.getNumber() + ", " + bean2.isPassed());
		
		
		//ApplicationContext 自动实例化和初始化非延迟加载的单例bean,
		//                   自动注册 BeanPostProcessor/BeanFactoryPostProcessor.
		
		System.out.println("time-elapsed: "  + stop.getTime() + "ms");
	}
	
	/**
	 * 一些内置的BeanPostProcessor( MergedBeanDefinitionPostProcessor ), such as:
	 * <br> AutowiredAnnotationBeanPostProcessor, (handle the @Autowired and @Value, JSR-330 @Inject)
	 * <br> RequiredAnnotationBeanPostProcessor, (handle @Required)
	 * <br> CommonAnnotationBeanPostProcessor (
	 * 							handle the JSR-250 annotations parsing, such as:
	 * <br>						@Resource, @PostConstruct, @PreDetroy.)
	 * <br> All above beanPostProcessors only handle the property-scope annatations.
	 * -----------------------------------------------------------------------------------------
	 * 
	 * <br> Regarding class-scope annotations: @Component, @Service, @Controller, @Repository, 
	 * <br>	they will be handled by ClassPathScanningCandidateComponentProvider class.
	 */

}

@Component("MyBeanFactoryPostProcessor")
class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor postProcessBeanFactory start...");
		
		String[] bdnames = beanFactory.getBeanDefinitionNames();
		BeanDefinition definition;
		for(String name : bdnames){
			definition = beanFactory.getBeanDefinition(name);
			System.out.println(definition.getBeanClassName() + "\t" + definition.getScope());
			
			//definition.setScope("singleton");
		}
		
		System.out.println("BeanFactoryPostProcessor postProcessBeanFactory end.");
	}
	
}

@Component("MyBeanPostProcessor")
class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessBeforeInitialization: " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessAfterInitialization: " + beanName);
		return bean;
	}
	
}

@Component("MyBean1")
class MyBean1 {
	
}

@Component("MyBean2")
@Scope("prototype")
class MyBean2 {
	
	/**
	 * do not require set method for the field
	 */
	@Resource(name="staticFactoryBean")
	private Integer prop1;
	
	@Value("#{999}")
	private int number;
	
	@Value("#{true}")
	private boolean isPassed;

	public Integer getProp1() {
		return prop1;
	}

	public int getNumber() {
		return number;
	}

	public boolean isPassed() {
		return isPassed;
	}
		
}