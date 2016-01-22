package org.jackJew.JNI;

/**
 * 
 * Description: JNI load 类 
 * @author zhurunjia
 *
 */
public class TestDLL {
	
	static {
		System.loadLibrary("NetRequestNew");
	}
	
	/**
	 *  add 方法
	 */
	public native boolean GetPhotoToFile(String paramString1,
			String paramString2, String paramString3, String paramString4,
			String paramString5);

}