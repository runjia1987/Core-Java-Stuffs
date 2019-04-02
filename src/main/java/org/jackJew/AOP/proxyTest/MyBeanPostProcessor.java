package org.jackJew.AOP.proxyTest;

import org.jackJew.AOP.transaction.declare.annotation.Anno;
import org.jackJew.AOP.transaction.declare.schema.ValidService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component("MyBeanPostProcessor")
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		//System.out.println("MyBeanPostProcessor beforeInit: " + beanName + ", type: " + bean.getClass());
    if (ValidService.class.isAssignableFrom(bean.getClass())
        || org.jackJew.AOP.transaction.declare.annotation.ValidService.class.isAssignableFrom(bean.getClass())) {
      System.out.println(bean.getClass());
    }
    if (bean.getClass().isAnnotationPresent(Anno.class)) {
      Anno anno = bean.getClass().getAnnotationsByType(Anno.class)[0];
      System.out.println(anno.name());
    }
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		//System.out.println("MyBeanPostProcessor afterInit: " + beanName);
		return bean;
	}
}
