package org.jackJew.mockito.implementation;

import static org.jackJew.mockito.implementation.Mock.*;

import java.util.HashMap;
import java.util.Map;

import org.jackJew.spring.controller.TestController;
import org.junit.Assert;
import org.springframework.test.util.ReflectionTestUtils;

public class TestClient {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TestController testController = new TestController();
		
		Map<String, String> map = mock(Map.class);
		ReflectionTestUtils.setField(testController, "map", map);
		
		when(map.get("key")).thenReturn("value");
		
		String result = testController.test();
		
		Assert.assertEquals("value", result);
		
		verify(map, times(1)).get("key");
		System.out.println("Assert.assertEquals true, verify true");
		
		// -----------------------------------------------------------------------------
		
		HashMap<String, String> hashMap = mock(HashMap.class);
		ReflectionTestUtils.setField(testController, "hashMap", hashMap);
		
		when(hashMap.get("key2")).thenReturn("value2");
		
		result = testController.test2();
		
		verify(hashMap, times(1)).get("key2");
		Assert.assertEquals("value2", result);
		
		System.out.println("Assert.assertEquals true, verify true");
		
		try {
			verify(hashMap, times(1)).get("key");  // fail
		} catch(IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
		try {
			verify(hashMap, times(2)).get("key2");  // fail
		} catch(IllegalArgumentException e) {
			Assert.assertNotNull(e);
		}
		
		when(hashMap.get(any(String.class))).thenReturn("value3");
		
		result = testController.test2();
	}
		
}
