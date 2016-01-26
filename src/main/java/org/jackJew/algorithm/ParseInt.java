package org.jackJew.algorithm;

public class ParseInt {

	public static int parseInt(String input) {
		String regex = "^[+|-]{0,1}\\d+$";
		if(!input.matches(regex)) {
			throw new IllegalArgumentException("input string can not parse to int.");
		} else {
			int result = 0, size = input.length();
			char sign = '\u0000';
			for(int i = 0; i < size; i++) {
				char c = input.charAt(i);
				if( i == 0) {
					if(c >= 48 && c <= 57) {
						result = c - 48;
					} else {
						sign = c;
					}
				} else {
					result *= 10;
					result += (c - 48);
				}
				// in ASCII, '0' is 48
			}
			if(sign != '\u0000') {
				if(sign == '-') {
					result = 0 - result;
				}
			}
			return result;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// testcases
		System.out.println(parseInt("123"));
		System.out.println(parseInt("+123"));
		System.out.println(parseInt("-123"));
		
		Thread.sleep(100);
		System.out.println(parseInt("abc"));
	}

}
