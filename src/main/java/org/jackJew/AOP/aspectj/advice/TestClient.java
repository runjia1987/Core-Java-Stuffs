package org.jackJew.AOP.aspectj.advice;

import java.util.Arrays;
import java.util.List;

import org.jackJew.ioc.beanSinletonPrototype.TargetProtoTypeBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 两种方式, 使用纯注解annotation（本例）或使用XML schema配置如下:
 * <aop:config>
	 <aop:aspect id="myAspect" ref="aBean">
		<aop:pointcut id="businessService"
			expression="execution(* com.myapp.service.*.*(..)) and this(service)"/>
		<aop:before pointcut-ref="businessService" method="monitor"/>
		...
	 </aop:aspect>
	</aop:config>
 * @author runjia
 * keywords 'and', 'or' and 'not' can be used in place of '&&', '||' and '!' respectively.
 */
public class TestClient {
	
	public static void main(String... args){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		JoinpointOperation operation = context.getBean(JoinpointOperation.class);
		//operation.plainMethod();
		List<?> list = operation.getList("jack is hero!!");  // service logic has 4
		System.out.println("after AOP advice, list.size is: " + list.size());  // 5
		
		
		JoinpointOperation2 operation2 = context.getBean(JoinpointOperation2.class);
		System.out.println(operation2.getClass());   // JoinpointOperation2$$EnhancerBySpringCGLIB$$xxxx
		System.out.println(Arrays.toString(operation2.getClass().getInterfaces()));
		operation2.call();
		
		TargetProtoTypeBean bean1 = (TargetProtoTypeBean)context.getBean("targetPrototypeBean");
		System.out.println(bean1);
		((AbstractApplicationContext)context).close();
	}
	
}
