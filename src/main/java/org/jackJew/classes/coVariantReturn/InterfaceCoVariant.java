package org.jackJew.classes.coVariantReturn;

public interface InterfaceCoVariant {

}

class Impl implements InterfaceCoVariant {
	
}

class Parents  {
	
	static int i = 123;
	
	public Parents(){
		i = 0;
	}

}

class Children extends Parents {

}

interface ParentInterface {
	Parents get();
	InterfaceCoVariant get2();
}

/**
 * 接口参数协变
 * @author zhurunjia
 */
class Specific implements ParentInterface {
	
	//Return type of overridden method is allowed to vary. 返回的类型协变
	public Children get() {
		return null;
	}

	@Override
	public Impl get2() {
		return null;
	}

}
