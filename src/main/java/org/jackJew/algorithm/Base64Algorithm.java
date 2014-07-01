package org.jackJew.algorithm;

import java.util.Arrays;


/**
 * base64 algorithms
 * @author Jack
 *
 */
public class Base64Algorithm {
	
	public final static String EncodingCharset = "UTF-8";
	
	public final static String Base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	/**
	 * divided by 3, three 8-bit chars seperated into four 6-bit chars, by padding in the high bits.
	 * <br> each trailing 000000 is converted to '='
	 * <br>
	 * Note: only supporting ascii chars (\u0000 ~ \u00FF) 
	 */
	public static char[] encode(byte[] sourceBytess){
		System.out.println(Arrays.toString(sourceBytess));
		
		int size = sourceBytess.length, i = 0, mod = 0, j = 0, resultSize = (size / 3) * 4;
		mod = size % 3;
		if (mod > 0)
			resultSize += 4;
		byte b1 = 0, b2 = 0;
		char[] resultChars = new char[resultSize];
		
		while(i < size) {
			mod = i % 3;
			if ( i > 0)
				b1 = sourceBytess[i - 1];
			else
				b1 = sourceBytess[i];
			b2 = sourceBytess[i];
			
			int index = 0;
			switch (mod) {
				case 0:
					index = b2 >> 2;
					System.out.println(index);
					resultChars[j++] = Base64Chars.charAt(b2 >> 2);
					break;
				case 1:
					index = ((b1 & 0x03) << 4) + (b2 >>> 4);
					resultChars[j++] = Base64Chars.charAt(index);
					break;
				case 2:
					index = ((b1 & 0x0f) << 2) + (b2 >>> 6);
					resultChars[j++] = Base64Chars.charAt(index);
					index = b2 & 0x3f;
					resultChars[j++] = Base64Chars.charAt(index);
					break;
			}			
			i++;
		}
		
		if ( mod == 0) {
			// four chars
			// trailing chars as exception '='
			resultChars[j++] = Base64Chars.charAt((b2 & 0x03) << 4);
			resultChars[j++] = '=';
			resultChars[j++] = '=';
		} else if(mod == 1) {
			// five chars
			resultChars[j++] = Base64Chars.charAt((b2 & 0x0f) << 2);
			resultChars[j++] = '=';
		}
		
		return resultChars;
	}
	
	/**
	 * testcase for encode & decode
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String sourceStr = "MTIzNDU2Nzg5c1Mk";
		char[] encodedResult = encode(sourceStr.getBytes(EncodingCharset));
		System.out.println(encodedResult);
		
	}

}
