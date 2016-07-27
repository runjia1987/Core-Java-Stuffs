package org.jackJew.interview;

import java.io.IOException;

public class MethodExceptionSignature {
	
	class Child implements Parent, Parent2{
		@Override
		public void print() {
			// throws InterruptedException 或不声明
		}

		@Override
		public void print2() { //取两个接口相同方法的声明异常的 /*交集*/ 或不声明
		}

		@Override
		public void print3() throws RuntimeException { //取两个接口相同方法的声明异常的 /*交集*/ 或不声明
		}
		
	}

}

interface Parent {
	abstract void print() throws InterruptedException;
	abstract void print2() throws IOException;
	abstract void print3() throws Exception;
}

interface Parent2 {
	abstract void print2() throws RuntimeException;
	abstract void print3() throws RuntimeException;
}