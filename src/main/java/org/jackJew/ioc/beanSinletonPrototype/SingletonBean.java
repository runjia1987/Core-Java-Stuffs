package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean implements ApplicationContextAware {
	
	private String name;
	private MyTargetBean prototypeBean;
	private ApplicationContext context;
	
	public void getNewPrototypeBean() {
		System.out.println();
		
		//与@Scope(value = "prototype")直接相关, 是否返回新实例
		this.prototypeBean = this.context.getBean(MyTargetBean.class);
	}
	
	public void print(){
		getNewPrototypeBean();
		
		System.out.println(this.name);
		System.out.println("获取的prototypeBean：" + this.prototypeBean.toString());
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
	}

	public void setName(String name) {
		this.name = name;
	}

}
