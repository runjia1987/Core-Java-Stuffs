package org.jackJew.classes.exceptions;

import java.util.Arrays;

public class ExceptionUsedInEffectiveJava {

	/**
	 * 《Effective Java》提出的常用异常
	 */
	public static void main(String[] args) {
		RuntimeException[] exceptions = {
				new IllegalArgumentException(),	//非法参数
				new IllegalStateException(),	//非法的对象状态, 例如对象未完成初始化
				new NullPointerException(),		//空指针错误
				new IndexOutOfBoundsException(),//下标索引越界
				new java.util.ConcurrentModificationException(),	//并发修改错误
				new UnsupportedOperationException()	//指定操作不受支持, 例如不可变集合类对象的尺寸改变操作
		};
		System.out.println(Arrays.toString(exceptions));
	}

}
