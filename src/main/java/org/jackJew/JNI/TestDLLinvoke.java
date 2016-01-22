package org.jackJew.JNI;

/**
 * 
 * Description: 测试JNI调用  
 * @author zhurunjia
 *
 */
public class TestDLLinvoke {
	
	public Class<?> get(){
		return null;
	}
	
	public static void main(String[] args){
		
		System.out.println(System.getProperty("java.library.path"));
		
		TestDLL td = new TestDLL();
		//boolean bool = td.GetPhotoToFile("123", "123", "123", "123", "123");
		
	}

}
