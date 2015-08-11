package org.jackJew.classes.initialization;


/**
 * [父子继承类的实例化时执行顺序]<br>
 * 			父类静态变量-静态初始化块 → 子类静态变量-静态初始化块 → <br>
 * 									父类实例变量-普通初始化块 → 父类构造器 → <br>
 * 									子类实例变量-普通初始化块 → 子类构造器
 * @author zhurunjia
 */
public class Child extends AbstractParent {
	
	private int number = 100;	//初始化为0, 直至<当前类>被实例化时才正常赋值
	private char character = 'a';		//初始化为'\u0000'
	
	{
		System.out.println("子类普通初始化块");
	}
	
	static {
		System.out.println("子类静态代码块");
	}
	
	//父类的构造方法不能被子类继承
	public Child(int i){
		super(i);   // 如果省略这行, 则自动调用父类的无参默认构造方法
		System.out.println("子类有参构造方法.");
		add();
	}
	
	public static void main(String[] args) {
		System.out.println(new Child(1).number);
		
		//StaticBlockIntializationClass sbi = null;	//不会执行StaticBlockIntializationClass类的任何初始化
	}

	@Override
	/**
	 * 父类: protected abstract void add();
	 */
	protected void add() {
		//number++;
		System.out.println("子类add()方法: number@" + number + "  " + (character == '\u0000'));
	
	}

}
