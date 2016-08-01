package org.jackJew.classes.genericSuperClass;

import com.google.gson.JsonObject;

public class MyHandler extends GenericParent<JsonObject, JsonObject> implements GenericInterface<String, Integer> {

	@Override
	JsonObject handle(JsonObject request) {
		JsonObject response = new JsonObject();
		response.addProperty("status", "success");
		response.addProperty("data", "test");
		return response;
	}

	@Override
	public Integer get(String key) {
		try {
			return Integer.valueOf(key);
		} catch(RuntimeException e) {
			return Integer.MIN_VALUE;
		}
	}

}
