package org.jackJew.classes;

/**
 * 抽象类不能被修饰为final（所有外部类都不允许修饰为private/protected）, 因为其需要被子类所继承
 * @author zhurunjia
 */
public abstract class AbstractClassCannotbeFinal {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
	}
	
	abstract void job();	//注意抽象不能被修饰为private或final, 因为其必被子类所可见和实现
	
	final void job2(){
		
	}

}
