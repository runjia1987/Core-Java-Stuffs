package org.jackJew.classes.innerclass;

import org.jackJew.classes.interfaces.Interface;

public class TestClass {
	
	public static void main(String... args){
		
		//普通内部类（抽象的, 有抽象方法需实现）
		OuterClass oc = new OuterClass();
		oc.new InnerClass1() {
			@Override
			void toImplement(){ }
		};
		
		//静态内部类（抽象的, 有抽象方法需实现）
		new OuterClass.InnerClass2() {
			@Override
			void job() { }			
		};
		
		//OuterClass.label = "";  //compile error
		
		Interface ni = null;
		
		//WithDefaultIdetifierInterface wii = null;  //compile error
		
		//System.out.println(ni.getClass());	//runtime error
	}

}
