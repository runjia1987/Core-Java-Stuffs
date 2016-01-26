package org.jackJew.JUnitTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.Base64Utils;

public class Base64TestForJdk8 {
	
	@Test
	public void testUrlEncoder() throws UnsupportedEncodingException {
		String input = "http://www.baidu.com/";
		String result = Base64.getUrlEncoder().encodeToString(input.getBytes());
		
		System.out.println(result);
		
		byte[] bytes = Base64Utils.encode(input.getBytes());
		String result2 = new String(bytes);
		System.out.println(result2);
		
		Assert.assertTrue(result.equals(result2));
		
		String result3 = URLEncoder.encode(input, "GBK");
		System.out.println(result3);
		
		input = "https://www.baidu.com/s?wd=";
		String urlParams = "xx yy:zz#a$b";
		String result4 = URLEncoder.encode(urlParams, "GBK");
		System.out.println(input + result4);  // this is proper way
		
	}

}
