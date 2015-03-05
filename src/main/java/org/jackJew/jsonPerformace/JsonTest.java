package org.jackJew.jsonPerformace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


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
		
		UserInfo info = new UserInfo(9999, "runjia", null);
		info.setSortValue(100);
		String serializeStr = JSON.toJSONString(info);		
		System.out.println(serializeStr);
		
		UserInfo info2 = JSON.parseObject(serializeStr, UserInfo.class);
		System.out.println("deserialize: " + info2.getSortValue());
		System.out.println(JSONUtil.toCompatibleJSONString(array));
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
		while(i++ < 1){
			test.useFastJson();
		}
		System.out.println((System.currentTimeMillis() - beginTime) + "ms cost.");
	}
	
}
