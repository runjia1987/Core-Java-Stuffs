package org.jackJew.interview;

import java.io.IOException;

public class MethodExceptionSignature {
	
	class Child implements Parent, Parent2{
		@Override
		public void print() {
			// throws Check Exception 都可以忽略
		}

		@Override
		public void print2() { //取两个接口相同方法的声明异常的 /*交集*/ 或不声明
		}
		
	}

}

interface Parent {
	abstract void print() throws InterruptedException;
	abstract void print2() throws IOException;
}

interface Parent2 {
	abstract void print2() throws RuntimeException;
}