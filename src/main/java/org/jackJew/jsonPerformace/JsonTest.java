package org.jackJew.jsonPerformace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 
 * Description: Json lib Test in speed
 * @author zhurunjia
 *
 */
public class JsonTest {
	
	/**
	 * use Google gson
	 */
	public void useGson() {		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		UserInfo userInfo = new UserInfo(9999, "runjia", null);
		String serializeStr = gson.toJson(userInfo);		
		System.out.println(serializeStr);
		
		UserInfo deserialized = gson.fromJson(serializeStr, UserInfo.class);
		System.out.println("deserialize: " + deserialized);
	}
	
	/**
	 * use json-lib library
	 */
	public void useJsonLib(){
		// TODO
	}

	public static void main(String[] args){
		long beginTime = System.currentTimeMillis();		
		JsonTest test = new JsonTest();
		
		//循环
		int i = 0;
		while(i++ < 100){
			test.useGson();
		}
		System.out.println((System.currentTimeMillis() - beginTime) + "ms cost.");
	}
	
}
