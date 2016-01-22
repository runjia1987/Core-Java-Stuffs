package org.jackJew.classes.innerclass;

/**
 * definition class
 * @author zhurunjia
 */
class ChildHere {	}

public class OuterClass {
	
	//private 修饰变量的可见性范围, static修饰变量类型
	private static String label = "abcdefg";	//不论多少个实例, 静态变量只分配一次内存(类加载,initializing)
	String string = "";
	
	private class InnerClass {		
		public InnerClass (){
			System.out.println("org.jackJew.classes.innerclass.OuterClass.InnerClass constructor called.");
		}
		
	}
	
	/**
	 * 非静态内部类
	 * @author zhurunjia
	 */
	abstract class InnerClass1 {
		public InnerClass1(){
			string = "123";
		}
		abstract void toImplement();
	}
	
	/**
	 * 静态内部类
	 * @author zhurunjia
	 */
	abstract static class InnerClass2 {
		
		public InnerClass2(){ }
		
		abstract void job();
	}	
	
	public static void main(String... args){
		
		InnerClass2 ic2 = new InnerClass2() {
			@Override
			void job() { }
		};
		
		ic2.job();	//本地类可访问内部类的私有方法job()
		
		//方法类的内部类
		class Test{
			void print(){
				System.out.println(label);
			}
		}
		new Test().print();
		
		//if语句块内的内部类
		if(label.length() > 1){
			class Test2 {
				void print(){
					System.out.println(label);
				}
			}
			new Test2().print();
		}
		
		//new Test2().print();	//compile error
	}
}
