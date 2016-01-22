package org.jackJew.classes;

/**
 * hashCode 的约定是：
 * (1) 在 Java 应用程序的一次执行期间，对同一对象多次调用 hashCode方法，必须返回相同的int整数，
 * 当然前提是对象所用的信息没有被修改。而在其他执行过程中生成的整数无需保持一致。
 * (2) 如果根据 equals(Object)方法，两个对象是相等的，那么对这两个对象调用 hashCode方法
 * 也必须生成相同的整数。 
 * (3) 如果根据 equals(Object)方法两个对象不相等，那么在两个对象上调用 hashCode方法
 * 不要求必须生成不同的整数结果。但是，为不相等的对象生成不同的整数可以提高哈希表的性能.
 * @author zhurunjia
 */
public final class FinalClassUsingHashCode {
	
	static int i = 9;
	
	public static void main(String... args){
		
		System.out.println(new Integer(i).hashCode());	 // 9
		
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2);	//true, 直接字面量存放在运行时常量池, 不会重复创建
		
		System.out.println(s1.hashCode() == 31*31*'a' + 31*'b' + 'c');  //true
		
		System.out.println("1".hashCode() + "\t" + "a".hashCode());	 //ascii码: 49, 97
	}

}
