package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.stereotype.Component;

@Component
public abstract class SingletonBean {
	
	private String name;
	
	protected abstract TargetProtoTypeBean getNewPrototypeBean();
	
	public void print(){
		TargetProtoTypeBean targetBean = getNewPrototypeBean();
		
		System.out.println("获取的prototypeBean：" + targetBean.toString());
		System.out.println("获取的prototypeBean class: " + targetBean.getClass());
	}

	public void setName(String name) {
		this.name = name;
	}

}
