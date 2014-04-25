package org.jackJew.AOP.proxyTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class MethodMatcherPointcutTest {

	/**
	 * test StaticMethodMatcherPointcut and DynamicMethodMatcherPointcut
	 */
	public static void main(String[] args) {
		/**
		 * notice: proxyFactoryBean will autodetectInterfaces represented by the target.
		 * <br>	   ProxyFactory will not autodetect intefaces.
		 */
		ProxyFactory pf = new ProxyFactory();  //there is a constructor taking target as parameter
		//pf.setTarget(null);
		
		ProxyFactoryBean pfb = new ProxyFactoryBean();
		pfb.setTarget(new TargetOperation());
		Advice advice = new SpecificAdvice();
		
		//pfb.setProxyTargetClass(true);
		//pfb.addAdvisor(new DefaultPointcutAdvisor(new MyStaticMethodMatcherPointcut(), advice));
		pfb.addAdvisor(new DefaultPointcutAdvisor(new MyDynamicMethodMatcherPointcut(), advice));
		
		Intr100 proxy = (Intr100)(pfb.getObject());
		proxy.print("abcdefg");  // will not be intercepted
		proxy.charge(new Date(), 999);  // will be intercepted and advised
		System.out.println("implemented interfaces: " + Arrays.toString(proxy.getClass().getInterfaces()));
		
		System.out.println(Set.class.isAssignableFrom(java.util.HashSet.class));
	}

}

/**
 * Around advice specified
 * @author runjia
 */
class SpecificAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("开始拦截方法....");
		System.out.println("截获的参数: " + Arrays.toString(mi.getArguments()));
		Object result = mi.proceed();
		System.out.println("方法拦截结束.");
		return result;
	}
	
}

class MyStaticMethodMatcherPointcut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return method.getName().startsWith("charge")
					&& Intr100.class.isAssignableFrom(targetClass);
	}
	
}

class MyDynamicMethodMatcherPointcut extends DynamicMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass, Object[] args) {
		return Intr100.class.isAssignableFrom(targetClass)
					&& (args != null && args.length == 2
							&& args[0] instanceof Date && args[1].getClass().equals(Integer.class));
	}
	
}

interface Intr100 {
	void print(String str);
	boolean charge(Date date, int i);
}

class TargetOperation implements Intr100 {

	@Override
	public void print(String str) {
		System.out.println("print() " + str);
	}

	@Override
	public boolean charge(Date date, int i) {
		System.out.println("charge(date, i) " + date + ", " + i);
		return false;
	}
	
}