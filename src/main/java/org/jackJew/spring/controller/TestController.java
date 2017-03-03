package org.jackJew.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.jackJew.ioc.beanSinletonPrototype.SingletonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * annotation RestController is composite of @Controller and @ResponseBody, new in 4.0;
 * DefaultAnnotationHandlerMapping handles @Controller and @RequestMapping,
 * by determineUrlsForHandler() and determineUrlsForHandlerMethods().
 * @author Jack
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private SingletonBean singletonBean;
	
	private Map<String, String> map;
	
	private HashMap<String, String> hashMap;
	
	@RequestMapping(value = "/call", method = RequestMethod.GET)
	public String call() {
		return "{OK:1}";
	}
	
	@RequestMapping(value = "/testRequestParam", method = RequestMethod.GET)
	public String testRequestParam(@RequestParam String tid) {
		return "tid: " + tid;
	}
	
	/**
	 * for test of {@link org.jackJew.mockito.implementation.Mock }
	 */
	public String test() {
		return map.get("key");
	}
	
	/**
	 * for test of {@link org.jackJew.mockito.implementation.Mock }
	 */
	public String test2() {
		return hashMap.get("key2");
	}

}
