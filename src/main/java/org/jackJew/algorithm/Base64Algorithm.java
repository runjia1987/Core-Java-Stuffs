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
	public static char[] encode(byte[] sourceBytes){
		System.out.println(Arrays.toString(sourceBytes));
		
		int size = sourceBytes.length, i = 0, mod = 0, j = 0, resultSize = (size / 3) * 4;
		mod = size % 3;
		if (mod > 0)
			resultSize += 4;
		byte b1 = 0, b2 = 0;
		char[] resultChars = new char[resultSize];
		
		while(i < size) {
			mod = i % 3;
			if ( i > 0)
				b1 = sourceBytes[i - 1];
			else
				b1 = sourceBytes[i];
			b2 = sourceBytes[i];
			
			int index = 0;
			switch (mod) {
				case 0:
					index = b2 >> 2;
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
	 * reverse the above procedure
	 * @param sourceChars
	 */
	public static byte[] decode(char[] sourceChars){
		final char exceptionChar = '=';
		
		int size = sourceChars.length, i = 0, j = 0, mod = 0, value = 0;
		if (size == 0 ||  size % 4 != 0)
			throw new IllegalArgumentException("input parameter is not legally Base64 encoded!");
		
		// will be reduced later (if needed)
		byte[] resultBytes = new byte[(size / 4) * 3];
		
		int i1 = 0, i2 = 0;
		char c1 = '\u0000', c2 = '\u0000';
		while(i < size) {
			c1 = sourceChars[i];
			if (exceptionChar == c1)
				i1 = 0;
			else
				i1 = Base64Chars.indexOf(c1) & 0xff;
			System.out.println("i1: " + i1);
			
			if(i < size - 1 ) {
				c2 = sourceChars[i + 1];
				if (exceptionChar == c2)
					i2 = 0;
				else
					i2 = Base64Chars.indexOf(c2) & 0xff;
				System.out.println("i2: " + i2);
			} else
				i2 = i1;
			
			mod = i % 4;
			switch (mod) {
				case 0 :
					value = (i1 << 2) + (i2 >>> 4);
					resultBytes[j++] = (byte) value;
					break;
				case 1 :
					value = ((i1 & 0x0f) << 4) + (i2 >>> 2);					
					resultBytes[j++] = (byte) value;
					break;
				case 2 : 
					value = ((i1 & 0x03) << 6) + (i2 & 0x3f);
					resultBytes[j++] = (byte) value;
					break;
			}
			System.out.println("value: " + value);
			i++;
		}
		
		if (exceptionChar == c1) {
			System.out.println("final char is '=' !");
			if(sourceChars[size - 2] == exceptionChar) {
				// double '=' trailing
				j -= 2;
			} else {
				// only one '=' trailing
				j -= 1;
				c1 = sourceChars[size - 3];
				c2 = sourceChars[size - 2];
				i1 = Base64Chars.indexOf(c1) & 0xff;
				i2 = Base64Chars.indexOf(c2) & 0xff;
				// fix
				value = ((i1 & 0x0f) << 4) + (i2 >>> 2);
				resultBytes[j] = (byte) value;
			}
		}
		// reduce
		byte[] finalResult = new byte[j];
		System.arraycopy(resultBytes, 0, finalResult, 0, j);
		return finalResult;
	}
	
	/**
	 * testcase for encode & decode
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// encode
		String sourceStr = "MTIzNDU2Nzg5c1Mk";
		char[] encodedResult = encode(sourceStr.getBytes(EncodingCharset));
		System.out.println(encodedResult);
		
		// decode
		char[] encodedChars = "MTIzNDU2Nzg5c1Mk".toCharArray();
		byte[] resultBytes = decode(encodedChars);
		System.out.println(new String(resultBytes, EncodingCharset));
	}

}
