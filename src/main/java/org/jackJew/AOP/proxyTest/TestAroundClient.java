package org.jackJew.AOP.proxyTest;

import java.lang.reflect.Method;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class TestAroundClient {

	/**
	 * 测试around advice功能
	 */
	public static void main(String[] args) {
		
		Interface1 target = new TargetClass1("自定义的一个构造参数");		
		ProxyFactoryBean bean = new ProxyFactoryBean();
		
		
		bean.addAdvice(new AroundAdvice());	
		
		bean.addAdvice(new ThrowingAdvice());
		bean.addAdvice(new BeforeAdvice());
		bean.addAdvice(new AfterReturnAdvice());
		bean.setTarget(target);
		
		//若setProxyTargetClass(true), 将使用Cglib2AopProxy创建AOP代理, which requires Cglib.jar on classpath;
		//若添加setInterfaces语句, 将使得DefaultAopProxyFactory createAopProxy()方法的
		// hasNoUserSuppliedProxyInterfaces(config)语句为false, 从而进入创建JdkDynamicAopProxy逻辑分支
		
		//bean.setInterfaces(new Class[] {Interface1.class} ); // will autoDetectInterfaces
		
		Interface1 proxy = (Interface1)bean.getObject();
		proxy.print(100, 200);
		
	}
}

class TestAroundClient2 {

	/**
	 * 测试around advice功能
	 */
	public static void main(String[] args) {
		/*******************************************************************************/
		Interface1 target = new TargetClass1("自定义的一个构造参数");
		ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        Advice beforeAdvice = new BeforeAdvice(); //advice 
        
        // also can use DynamicMethodMatcherPointcut(动态切入, 即检查运行时传入的参数)
        Pointcut pointcut = new StaticMethodMatcherPointcut() {
			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return true;
			}
		}; //切入点
		
        DefaultPointcutAdvisor dda = new DefaultPointcutAdvisor(pointcut, beforeAdvice);
        factoryBean.addAdvisor(dda);
        factoryBean.setTarget(target);
        //factoryBean.setProxyTargetClass(false); // default is false
         
        Interface1 proxy = (Interface1)factoryBean.getObject();
        proxy.print(100, 200);
	}

}