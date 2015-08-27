package org.jackJew.classes.initialization;

public abstract class AbstractParent {
	
	protected abstract void print();
	
	{
		System.out.println("抽象父类普通初始化块");
	}
	
	static {
		System.out.println("抽象父类静态代码块");
	}
	
	/**
	 * 如果不显式定义默认构造方法, 则子类的构造器会报错, 除非显式调用父类的某个有参构造器
	 */
	public AbstractParent() {
		System.out.println("抽象父类无参构造方法.");
		print();
	}
	
	/**
	 * 有参构造方法
	 */
	public AbstractParent(int i){
		System.out.println("抽象父类有参构造方法.");
		print();
	}
	
	public static void main(String[] args) {
		System.out.println(new Child(1));
	}

}
