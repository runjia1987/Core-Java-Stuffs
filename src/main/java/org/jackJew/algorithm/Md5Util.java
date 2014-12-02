package org.jackJew.algorithm;

import java.security.MessageDigest;

/**
 * generate md5 string
 * 
 * @author Jack
 *
 */
public class Md5Util {

	public final static String MD5 = "MD5";
	public final static String Encoding = "UTF-8";
	public final static char[] HexCharArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String getMD5(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			byte[] resultBytes = md.digest(content.getBytes(Encoding));

			StringBuilder builder = new StringBuilder(resultBytes.length * 2);
			for (int i = 0; i < resultBytes.length; i++) {
				byte b = resultBytes[i];
				builder.append(HexCharArray[(b >> 4) & 0x0f]);
				builder.append(HexCharArray[(b & 0x0f)]);
			}
			return builder.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * test case
	 */
	public static void main(String[] args) {
		System.out.println(Md5Util.getMD5("i am good at english writing"));
	}

}
