package org.jackJew.classes.OverrideAndOverload;

import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.Stack;

/**
 * 方法覆盖
 * @author zhurunjia
 */
public class OverrideTest {

	static class A {
		String job(){ return "A class"; }
	}
	
	static class B extends A {
		@Override
		String job() {
			return "B class";
		}
	}
	
	static class C extends B {
		@Override
		String job() {
			return "C class";
		}
	}
	
	/**
	 * 对于overload重载方法的选择是静态的, 编译期即确定; <br>
	 * 对于override覆盖(重写)方法的选择是动态的, 运行期确定.
	 */
	public static void main(String[] args) {
		A array[] = new A[] {new A(), new B(), new C()};
		
		for(A a: array){
			System.out.println(a.job());	//各自输出指定的方法
		}
		
	}

}
