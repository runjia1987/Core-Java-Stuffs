package org.jackJew.spring.MyMapperScanner;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.StringUtils;

public class ClassPathScanner extends ClassPathBeanDefinitionScanner {
	
	private String jdbcTemplateName;
	
	public ClassPathScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
		if(beanDefinitionHolders == null || beanDefinitionHolders.isEmpty()) {
			return null;
		}
		for(BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
			AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanDefinitionHolder.getBeanDefinition();
			logger.info("now registering MyFactoryBean for detected component: "
						+ beanDefinition.getBeanClassName());
			
			beanDefinition.getPropertyValues().add("mapperInterface", beanDefinition.getBeanClassName());
			
			beanDefinition.setBeanClass(MyFactoryBean.class);
			
			if(StringUtils.hasText(jdbcTemplateName)) {
				beanDefinition.getPropertyValues().add("jdbcTemplate", new RuntimeBeanReference(jdbcTemplateName));
			}
			
			beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}
		return beanDefinitionHolders;
	}

	public void registerFilters() {		
		/*
		addIncludeFilter(new TypeFilter() {			
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				return false;
			}
		});*/
		// in JDK8 functional interface style
		addIncludeFilter((metadataReader, metadataReaderFactory) -> {
			final String className = metadataReader.getClassMetadata().getClassName();
			if(className.endsWith("Dao")) {
				// class name with suffix "Dao"
				System.out.println("TypeFilter checking passed for " + className);
				return true;
			}
			return false;
		});
		addExcludeFilter((metadataReader, metadataReaderFactory) -> {
			return false;
		});
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		// invoked by doScan() -> findCandidateComponents()
		boolean isCandidateComponent =
				beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
		System.out.println(beanDefinition.getBeanClassName() + " isCandidateComponent: " + isCandidateComponent);
		
		return isCandidateComponent;
	}

	public void setJdbcTemplateName(String jdbcTemplateName) {
		this.jdbcTemplateName = jdbcTemplateName;
	}

}
