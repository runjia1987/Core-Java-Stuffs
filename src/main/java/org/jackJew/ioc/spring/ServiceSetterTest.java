package org.jackJew.ioc.spring;

import javax.annotation.Resource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("testService1")
public class ServiceSetterTest {
	
	@Resource(name="service1")
	private Service1 service1;
	
	@Resource(name="service2")
	private Service2 service2;

	public static void main(String[] args) {
		// we have to use ApplciationContext to put @Resource into effects, not BeanFactory.
		BeanFactory context = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		
		ServiceSetterTest bean = context.getBean("testService1", ServiceSetterTest.class);
		System.out.println(bean); //object
		System.out.println(bean.getService2());  //null
		System.out.println(bean.getService1());  //null
		
		System.out.println(context.getBean("service1")); //object
		System.out.println(context.getBean("service2")); //object
		
	}

	public Service1 getService1() {
		return service1;
	}

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}

	public Service2 getService2() {
		return service2;
	}

	public void setService2(Service2 service2) {
		this.service2 = service2;
	}

}

@Service("service1")
class Service1 {
	
	@Resource(name="dao1")
	private DataDao1 dao1;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(String container){
		System.out.println("org.jackJew.ioc.spring.service1.save(String): " + container);
	}

	public DataDao1 getDao1() {
		return dao1;
	}

	public void setDao1(DataDao1 dao1) {
		this.dao1 = dao1;
	}
	
}

@Repository("dao1")
class DataDao1 {
	
	public DataDao1() { }
	
}
