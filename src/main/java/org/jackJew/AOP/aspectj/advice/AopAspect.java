package org.jackJew.AOP.aspectj.advice;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * notice: !!! the order of the advice defition will affect the actual advice injection.
 *
 */
@Component
@Aspect
public class AopAspect {
	
	@Pointcut("execution(public * *..JoinpointOperation.*(..))")
	void pointcutDef(){  }
	
	/**
	 * 引用pointcut表达式方法pointcutDef()，可以为full-qualified name.
	 */
	@Before("pointcutDef()")
	void beforeAdvice(){
		System.out.println("beforeAdivce executed.");
	}
	
	/**
	 * within = declaring type
	 * this = proxy type, this(com.xyz.service.AccountService)
	 * target = target object type
	 * args = runtime object takes argument with the given type, args(java.io.Serializable)
	 * @within, @within(org.springframework.transaction.annotation.Transactional)
	 * @target, @target(org.springframework.transaction.annotation.Transactional)
	 * @annotation = joinpoint methods take this annotation, @annotation(org.springframework.transaction.annotation.Transactional)
	 * @args = method arguments is single or more and its runtime type takes this annotation, @args(com.xyz.security.Classified)
	 * bean = joinpoints on spring bean name, bean(tradeService), bean(*Service), wildcard etc.
	 */
	@AfterReturning(pointcut="pointcutDef() && args(id) && within(org.jackJew.AOP..JoinpointOperation+)", returning="list")
	void afterReturningAdviceAdvanced(String id, List<String> list){
		list.add("999");  // to modify the list object from logic.
		System.out.println("afterReturningAdviceAdvanced executed. id: " + id + ", list size: " + list.size());
	}
	
	@Before("@args(accountable,..) && pointcutDef() && args(..,str)")
	void beforeTestAtArgs(Accountable accountable, String str){
		System.out.println("before advice Test@args...." + accountable.isAccount() + ", str: " + str);
	}
		
	/**
	 * matches all normally ending methods
	 */
	@AfterReturning("pointcutDef()")
	void afterReturningAdvice() {
		System.out.println("another afterReturningAdvice executed.");
	}
	
	@After("org.jackJew.AOP.aspectj.advice.AopAspect.pointcutDef()()")
	void afterFinallyAdvice(){
		System.out.println("afterFinallyAdvice executed.");
	}
	
	
	@Around("pointcutDef()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("aroundAdvice start....");
		Object result = pjp.proceed();
		System.out.println("aroundAdvice end.");
		return result;
	}

}