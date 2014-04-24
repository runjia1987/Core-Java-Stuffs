package org.jackJew.jsonPerformace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * Description: Json lib Test in speed
 * @author zhurunjia
 *
 */
public class JsonTest {
	
	private List<String> getList(){
		List<String> list = new ArrayList<String>();
		list.add("本地");
		list.add("中华人民共和国");
		list.add("IP 地址");
		return list;
	}
	
	/**
	 * use alibaba fastJson library
	 */
	public void useFastJson(){
		
		com.alibaba.fastjson.JSONObject n = new com.alibaba.fastjson.JSONObject();
		n.put("nid", 12345678);
		n.put("title", false);
		n.put("media", new UserInfo(9999, "runjia", "210987"));
		n.put("list", getList());
		n.put("issueDate", new Date());
		
		com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
		array.add(n);
		
		//List<UserInfo> userList = new ArrayList<UserInfo>();
		//for(int i = 0; i < 5; i++)
		//	userList.add(new UserInfo(9999, "runjia", null));
		
		//System.out.println(JSON.toJSONString(userList));
		System.out.println(JSONUtil.toCompatibleJSONString(array));
	}
	
	/**
	 * use json-lib library
	 */
	public void useJsonLib(){
		
	}

	public static void main(String[] args){
		long beginTime = System.currentTimeMillis();
		
		JsonTest test = new JsonTest();
		
		//循环
		int i = 0;
		while(i++ < 100){
			//test.useJsonLib();
			test.useFastJson();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println((endTime - beginTime) + "ms cost.");
	}
	
}
