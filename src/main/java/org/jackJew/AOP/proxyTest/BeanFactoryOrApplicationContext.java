package org.jackJew.AOP.proxyTest;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
		/**
		 * use DefaultListableBeanFactory, not XmlBeanFactory(which is @Deprecated).
		 */
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory);
		beanDefinitionReader.loadBeanDefinitions(
				new ClassPathResource("applicationContext.xml"));
		
		//ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		BeanFactoryPostProcessor bfp = new MyBeanFactoryPostProcessor();
		bfp.postProcessBeanFactory(factory);  // print all beanDefinitions
		
		factory.addBeanPostProcessor(new MyBeanPostProcessor());
		
		MyBeanTest myBeanTest = factory.getBean("MyBeanTest", MyBeanTest.class);		//prototype
		System.out.println(myBeanTest.getProp1() + ", " + myBeanTest.getNumber() + ", " + myBeanTest.isPassed());
		
		// DefaultListableBeanfactory implements ConfigurableBeanFactory#destroySingletons()
		factory.destroySingletons();
		
		//ApplicationContext 自动实例化和初始化非延迟加载的单例bean,
		//                   自动注册 BeanPostProcessor/BeanFactoryPostProcessor.
		
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
	 * <br>	they will be handled by ClassPathBeanDefinitionScanner | ClassPathScanningCandidateComponentProvider.
	 */
}

@Component("MyBeanFactoryPostProcessor")
class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor postProcessBeanFactory start...");
		
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for(String name : beanDefinitionNames){
			BeanDefinition definition = beanFactory.getMergedBeanDefinition(name);
			String scope = definition.getScope();
			
			System.out.println(definition.getBeanClassName() + "\t"
					+ (StringUtils.isEmpty(scope) ? ConfigurableBeanFactory.SCOPE_SINGLETON : scope));
		}		
		System.out.println("BeanFactoryPostProcessor postProcessBeanFactory end.");
	}
	
}

@Component("MyBeanPostProcessor")
class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("MyBeanPostProcessor beforeInit: " + beanName + ", type: " + bean.getClass());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("MyBeanPostProcessor afterInit: " + beanName);
		return bean;
	}	
}

@Component("MyBeanTest")
@Scope("prototype")
class MyBeanTest {
	
	/**
	 * do not require set method for the field,
	 * post-processed by CommonAnnotationBeanPostProcessor
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