package org.jackJew.classes.variableInitialize;

public class ClassWithNullUseField {

	private static String field = "this is a static field";
	
	ClassWithNullUseField get(){
		return null;	//返回null
	}
	
	public static String job(){
		return "abcd";
	}
	
	public static void main(String[] args) {
		System.out.println(new ClassWithNullUseField().get().field);
		//正常打印, 不报错
		System.out.println( ((ClassWithNullUseField)null).job() );
		//正常打印, 不报错
	}

}
