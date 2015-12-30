package org.jackJew.spring.controller;

import org.jackJew.ioc.beanSinletonPrototype.SingletonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private SingletonBean singletonBean;
	
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	public String call() {
		return "{OK:1}";
	}

}