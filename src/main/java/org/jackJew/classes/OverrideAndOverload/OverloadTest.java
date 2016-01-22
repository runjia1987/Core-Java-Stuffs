package org.jackJew.classes.OverrideAndOverload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 方法重写
 * @author zhurunjia
 */
public class OverloadTest {
	
	static void classify(Set<?> s){
		System.out.println("Set type");
	}
	
	static void classify(List<?> l){
		System.out.println("List Type");
	}
	
	static void classify(Collection<?> c){
		System.out.println("Collection Type");
	}

	/**
	 * 对于overload重载方法的选择是静态的, 编译期即确定; <br>
	 * 对于override覆盖(重写)方法的选择是动态的, 运行期确定.
	 */
	public static void main(String[] args) {
		OverloadTest test = new OverloadTest();
		
		Collection<?> cls[] = new Collection<?>[] {
				new HashSet<String>(),
				new ArrayList<String>(),
				new HashMap<String, Object>().values()
				};
		
		for(Collection<?> cl : cls) {
			classify(cl);	//output: Collection Type
			//若需要按类型输出, 需要在classify方法做instanceof验证
		}
		
		Object[] objects = new Object[]{ new Object(), new String("123") };
		for (Object o : objects) {
			test.print(o);
		}		
	}
	
	void print(Object s) {
		System.out.println("Object");
	}
	
	void print(String s) {
		System.out.println("String");
	}

}
