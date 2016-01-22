package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public abstract class SingletonBean {
	
	private String name;
	
	/**
	 * public | protected <non-static> method ()
	 * <br/>
	 * this method will be overriden by the CGlib generated subclass(requires CGlib)
	 * <br/> notice this method should have zero arguments!!!
	 */
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
