package org.jackJew.stringRegex;

public class RegexTest {

	/**
	 * 正则式测试
	 */
	public static void main(String[] args) {
		//日期
		System.out.println("2012-03-07".matches("^\\d{4}-\\d{2}-\\d{2}$"));

		//IP
		System.out.println("10.25.67.188".matches("^([1,2]*\\d{1,2}\\.){3}[1,2]*\\d{1,2}$"));
		
		System.out.println("1:2:3:4".split(":").length);
	}

}
