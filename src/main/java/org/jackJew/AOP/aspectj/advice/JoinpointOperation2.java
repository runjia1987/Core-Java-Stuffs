package org.jackJew.AOP.aspectj.advice;

import org.springframework.stereotype.Component;

@Component
public class JoinpointOperation2 {
	
	public void call() {
		System.out.println("call");
	}

}
