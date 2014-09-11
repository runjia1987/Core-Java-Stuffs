package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean2 implements BeanFactoryAware {
	
	private BeanFactory beanFactory;
	private String name;
	private TargetProtoTypeBean prototypeBean;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void getNewPrototypeBean() {
		System.out.println();
		
		//与@Scope(value = "prototype")直接相关, 是否返回新实例
		this.prototypeBean = this.beanFactory.getBean(TargetProtoTypeBean.class);
	}
	
	public void print(){		
		getNewPrototypeBean();
		
		System.out.println(this.name);
		System.out.println("获取的prototypeBean：" + this.prototypeBean);
	}
	
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.beanFactory = arg0;
	}

}
