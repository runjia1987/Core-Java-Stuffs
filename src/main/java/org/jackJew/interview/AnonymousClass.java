package org.jackJew.interview;

public class AnonymousClass {
	
	private String name;
	
	public AnonymousClass(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public static void main(String[] args) {		
		new AnonymousClass("abc").doJob();
		
		//java.util.Date.parse("19900909");  // @Deprecated
	}
	
	public void doJob(){
		final Object obj = new Object();
		
		new AnonymousClass("jack"){	// 匿名内部类，相当于 class Inner extends AnonymousClass { }
			private void anotherMethod(){  // 继承与方法扩展
				System.out.println(getName());
				System.out.println(obj);
			}
		}.anotherMethod();   // output: jack
	}

}
