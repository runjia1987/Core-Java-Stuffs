package org.jackJew.AOP.proxyTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

public class LookupMethodBeanTest {

	/**
	 * <lookup-method name="" bean=""/> 主要是为了实现单例bean依赖于原型bean的问题
	 */
	public static void main(String[] args) {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		
		for(int i = 0; i< 5; i++) {
			// output different instances on each call
			SingletonBean singletonBean = (SingletonBean)bf.getBean("methodLookupBean");
			System.out.println(singletonBean.getPrototypeBean());			
		}		
	}

}

abstract class SingletonBean {
	
	/**
	 * this method will be overriden by the dynamically generated subclass(requires Cglib)
	 * <br> notice this method should have zero arguments!!!
	 */
	protected abstract Interface22 getPrototypeBean();
	
	/**
	 * to do some logic here
	 */
	public void someJob(){
		Interface22 instance = getPrototypeBean();
		instance.print(12345);
	}
	
}

interface Interface22 {
	
	void print(int sss);
	
}

/**
 * 定义一个bean, scope = prototype,
 * <br> ensure that there is default non-arg constructor for this target class
 * @author runjia
 */
@Component("PrototypeBean")
@Scope("prototype")
class PrototypeBean implements Interface22 {

	@Override
	public void print(int sss) {
		System.out.println(this.toString() + "PrototypeBean print: " + sss);
	}
	
}
