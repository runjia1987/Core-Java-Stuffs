package org.jackJew.classes.genericSuperClass;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * test case
 * @author zhurunjia192
 *
 */
public class Detecter {
	
	public static void main(String[] args) {
		MyHandler myHandler = new MyHandler();
		System.out.println(myHandler.handle(new JsonObject()));
		System.out.println(myHandler.get("888"));
		
		Class<?> cls = MyHandler.class;
		System.out.println(cls);
		
		Class<?> superClass = cls.getSuperclass();
		System.out.println(superClass);
		
		java.lang.reflect.Type t1 = cls.getGenericSuperclass();
		ParameterizedType pt1 = (ParameterizedType)t1;
		System.out.println(Arrays.toString(pt1.getActualTypeArguments()));
		
		java.lang.reflect.Type[] t2 = cls.getGenericInterfaces();
		System.out.println("getGenericInterfaces size: " + t2.length + ": " + t2[0]);
		ParameterizedType pt2 = (ParameterizedType)t2[0];
		System.out.println(Arrays.toString(pt2.getActualTypeArguments()));
		
		Map<String, String> params = new HashMap<>();
		params.put("url", "https://www.baidu.com");
		String serialize = new Gson().toJson(params);
		System.out.println(serialize);
		Map<String, String> deserialize = new Gson().fromJson(serialize, Map.class);
		System.out.println(deserialize.get("url"));
	}

}
