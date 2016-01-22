package org.jackJew.designPattern;

/**
 * 安全枚举类, 使用==号做比较, 不需要创建多余对象,
 * <br> 这是一种实例受控（instance-controlled）的类
 * @author zhurunjia
 */
public class TypeSafeEnumClass {
	
	private String symbol;
	
	public TypeSafeEnumClass(String symbol){
		this.symbol = symbol;
	}
	
	public final static TypeSafeEnumClass T1 = new TypeSafeEnumClass("T1");
	public final static TypeSafeEnumClass T2 = new TypeSafeEnumClass("T2");
	public final static TypeSafeEnumClass T3 = new TypeSafeEnumClass("T3");

}
class Client{
	
	public static void main(String... args){
		System.out.println(TypeSafeEnumClass.T1 == TypeSafeEnumClass.T2);
	}
	
}
