package org.jackJew.ioc.beanSinletonPrototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

interface MyTargetBeanIntr {
	
	void print();
	
}
/**
 * 注意作用域的定义：@Scope(value = "prototype")
 * @author zhurunjia
 */
@Component("myTargetBean")
@Scope(value = "prototype")
public class MyTargetBean implements MyTargetBeanIntr {

	private String value;

	public void print() {
		System.out.println(value);
	}

	public void setValue(String value) {
		this.value = value;
	}

}
