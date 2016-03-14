package org.jackJew.ioc.spring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiniBean {
	
	// processed by AutowiredAnnotationBeanPostProcessor, AutowiredFieldElement inner class.
	// beanFactory.resolveDependency(descriptor, beanName, autowiredBeanNames, typeConverter);
	// org.springframework.beans...DefaultListableBeanFactory.determinePrimaryCandidate(Map<String, Object>, DependencyDescriptor)
	
	@Autowired
	private java.util.Date startDate;
	
	@Autowired
	private java.util.Date endDate;
	
	public long compute() {
		return endDate.getTime() - startDate.getTime();
	}

}
