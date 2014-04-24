package org.jackJew.classes.coVariantReturn;

/**
 * 抽象类参数协变
 * @author zhurunjia
 */
public class AbstractClassCoVariant {
	
	AbstractClassCoVariant factoryGet() { return null; }
	
	void app(AbstractClassCoVariant a){ }

}

class ConcreteClass extends AbstractClassCoVariant {

	@Override
	ConcreteClass factoryGet() {	//返回的类型协变
		return null;
	}

	@Override
	void app(AbstractClassCoVariant a) {		
	}
	
}
