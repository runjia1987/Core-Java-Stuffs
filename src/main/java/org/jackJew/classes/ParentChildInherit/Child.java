package org.jackJew.classes.ParentChildInherit;

import java.util.Date;
import java.util.List;

public class Child extends Parent {

	int val = 999;
	static String XYZ = "Child static value";

	/*
	 * Class initialization refers to the computation and assignment of initial values specified by the programmer to the static fields of a class. It is not to be confused with preparation, which refers to the assignment of default values to each static field when the class is created - null to
	 * reference types, 0 to numeric types, etc.
	 */
	private Date date = new Date();

	{
		System.out.println("子类普通初始化块");
	}

	// 实例变量在类加载时linking(verification of binary representation,
	// preparation of class or interface types,
	// resolution of symbolic references)与initialization期间
	// 仅被初始化为null或原始类型的默认值, 需要等到本类构造器被调用时才正常赋值.
	// 静态变量(static variables)则在类加载时被正常初始化(initialization阶段).

	public Child() {
		super(1000);
		System.out.println("子类构造方法被调用.");
	}

	public static void main(String[] args) {
		// 不要在父类的构造函数中调用可能被子类覆盖的方法，如public和protected级别的域方法。
		// 由于父类的初始化早于子类的初始化，如果此时调用的方法被子类覆盖，而子类中的此覆盖方法中又引用了子类中尚未初始化的成员变量，
		// 这将很容易导致NullPointerException异常被抛出.

		Child child = new Child();
		child.unifiedJob();

		// new Parent().dosth();

		System.out.println("\n test id static variables and methods can be overridden...");

		Parent instance = new Child();
		// Child instance = new Child(); // 静态变量与静态方法的输出结果, 是不同于上面的
		System.out.println(instance.XYZ);
		System.out.println(instance.getStr());

		System.out.println(instance.getABC());
		System.out.println(child.getVal());  // 100
		System.out.println(child.val); // 999
		Parent _p = child;
		System.out.println(_p.val);  // 100
	}

	@Override
	@SuppressWarnings("deprecation")
	protected final void dosth() {
		if (date == null) {
			System.out.println("child dosth() date field is null !!!");
		} else
			System.out.println("child dosth() date field is " + date.getDate());
	}

	static String xyz = "child static value";

	static String getStr() {
		return xyz;
	}

	@Override
	protected List<String> getList() {
		return null;
	}

}
