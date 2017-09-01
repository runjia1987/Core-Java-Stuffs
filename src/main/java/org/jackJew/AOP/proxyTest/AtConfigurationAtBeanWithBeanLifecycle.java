package org.jackJew.AOP.proxyTest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jackJew.classes.interfaces.Interface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * In case of multiple @Configuration classes,
 * later @Bean definitions will override ones defined in earlier loaded files.
 * @author Jack
 *
 */
@Configuration
public class AtConfigurationAtBeanWithBeanLifecycle {
	
	/**
	 * if scope=singleton, when applicationContext starts,
	 * <br> will start this bean's initialization lifecycle.
	 */
	@Bean(name="AA", initMethod="init", destroyMethod="destroyed")
	@Scope("prototype")
	AA getAA (){
		return new AA();
	}
	
	@Bean(name={"BB", "BBBBB"})   //self define bean alias names, could be array[]
	ABB getBB(){
		return new BB();
	}

	@Bean
	Integer createInt() { // only create once
		System.out.println("create int");
		return Integer.MAX_VALUE;
	}

	@Bean
	String createString() { // only create once
		System.out.println("create string");
		return String.valueOf(createInt());
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(
										AtConfigurationAtBeanWithBeanLifecycle.class);
		// new AnnotationConfigApplicationContext(String... basePackages)
		// the constructors will automatically call refresh()
		
		AA instance = acac.getBean("AA", AA.class);
		
		// acac.register(class1, class2);
		// acac.scan(basePackages);
		//acac.refresh();
		
		IBB ibb = acac.getBean("BB", BB.class);
		ibb.print();
		
		ConfigurableBeanFactory cbf = (ConfigurableBeanFactory)acac.getAutowireCapableBeanFactory();
		// test the bean destruction cycle
		cbf.destroyBean("AA", instance);
		cbf.destroySingletons();
	}

}

/**
 * Bean life cycle: <br>
 * instantiation, property population(dependency injection, xml property setter methods),
 * callback awares(invokeAwareMethods() -> BeanNameAware, BeanFactoryAware, BeanClassLoaderAware),
 * initialization(BeanPostProcessor and sth else, execution order:
 * 						@PostConstruct-> InitializingBean-> custom init-method ),
 * ready, destruction(execution order:
 * 						@PreDestroy-> DisposableBean-> custom destroy-method)
 * public class ApplicationContextAwareProcessor implements BeanPostProcessor { -> ApplicationContextAware }
 * public class InitDestroyAnnotationBeanPostProcessor
		implements MergedBeanDefinitionPostProcessor { -> @PostConstruct }
 * @author runjia
 */
class AA implements InitializingBean, DisposableBean, ApplicationContextAware {
	
	@PostConstruct
	public void postConstrut(){
		System.out.println("Bean life cycle: postConstruct.");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean afterPropertiesSet().");
	}
	
	public void init(){
		System.out.println("custom init-method invoked.");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("Bean life cycle: preDestroy.");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean destroy().");		
	}
	
	public void destroyed(){
		System.out.println("custom destroy-method invoked.");
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		System.out.println("ApplicationContextAware setApplicationContext() : " + context.getBeanDefinitionCount());
	}
	
}

interface IBB {
	void print();
}
abstract class ABB {
	private String desc;
	private void dd(){}
	
}
class BB extends ABB implements IBB {

	@Override
	public void print() {
		System.out.println("\nBB print. \n");
	}
	
}
