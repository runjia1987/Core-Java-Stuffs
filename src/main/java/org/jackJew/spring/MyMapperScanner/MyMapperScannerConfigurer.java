package org.jackJew.spring.MyMapperScanner;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

public class MyMapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
	
	/**
	 * the basePackge to scan
	 */
	private String basePackage;
	
	private String jdbcTemplateName;
	
	private ApplicationContext applicationContext;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// do nothing.
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("postProcessBeanDefinitionRegistry hook started.");
		
		// default behavior, unlike MyBatis MapperScannerConfigurer
		processPropertyPlaceHolders();
		
		ClassPathMapperScanner classPathMapperScanner = new ClassPathMapperScanner(registry);
		classPathMapperScanner.setJdbcTemplateName(jdbcTemplateName);
		classPathMapperScanner.setResourceLoader(applicationContext);
		classPathMapperScanner.registerFilters();
		classPathMapperScanner.scan(
				StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}
	
	/*
	   * BeanDefinitionRegistries are called early in application startup, before
	   * BeanFactoryPostProcessors. This means that PropertyResourceConfigurers will not have been
	   * loaded and any property substitution of this class's properties will fail. To avoid this, find
	   * any PropertyResourceConfigurers defined in the context and run them on this class's bean
	   * definition. Then update the values.
	*/
	private void processPropertyPlaceHolders() {
	    Map<String, PropertyPlaceholderConfigurer> ppcs = applicationContext.getBeansOfType(PropertyPlaceholderConfigurer.class);

	    System.out.println("PropertyPlaceholderConfigurers detected.");
	    AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
	    
	    if (!ppcs.isEmpty() && beanFactory instanceof ConfigurableListableBeanFactory) {
	    	System.out.println("PropertyPlaceholderConfigurers postProcessBeanFactory beanfctory.");
	    	for (PropertyResourceConfigurer ppc : ppcs.values()) {
		        ppc.postProcessBeanFactory((ConfigurableListableBeanFactory)beanFactory);
		      }
	    } else {
	    	throw new IllegalStateException("not a ConfigurableListableBeanFactory.");
	    }
	  }
	  

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getJdbcTemplateName() {
		return jdbcTemplateName;
	}

	public void setJdbcTemplateName(String jdbcTemplateName) {
		this.jdbcTemplateName = jdbcTemplateName;
	}

}
