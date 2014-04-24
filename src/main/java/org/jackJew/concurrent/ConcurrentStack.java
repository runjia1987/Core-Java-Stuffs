package org.jackJew.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Stack;

/**
 * 基于无锁算法 - CAS(lock-free)的并发栈实现, 先进后出 <br>
 * 
 * 在轻到中度的争用情况下，非阻塞算法的性能会超越阻塞算法，因为CAS的多数时间都在第一次尝试时
 * 就成功，而发生争用时的开销也不涉及线程挂起和上下文切换，只多了几个循环。
 * 没有争用的 CAS 要比没有争用的锁便宜得 多，且争用 的CAS比争用的锁获取涉及更短的延迟。
 * 但是在高度争用的情况下（即有多个线程 不断争用一个内存位置的时候），
 * 基于锁的算法开始提供比非阻塞算法更好的吞吐率，因为当线程阻塞时，它就会停止争用，
 * 耐心地等候轮到自己，从而避免了进一步争用。
 * 
 * @author zhurunjia
 */
public class ConcurrentStack<E> {
	//JDK java.util.Stack 继承Vector, 基于数组实现, Object[] elementData

	// push和pop操作只需维护好head头节点
	private final AtomicReference<Node<E>> atomicHead = new AtomicReference<Node<E>>();

	/**
	 * 入栈
	 */
	void push(E item) {
		Node<E> newHead = new Node<E>(item);	//新建头元素
		Node<E> currentHead = null;
		do {
			currentHead = atomicHead.get();	//当前头元素
			newHead.next = currentHead;	//指针, 有可能为null
						
			System.out.println("null? : " + (null == currentHead));
			
		} while( !atomicHead.compareAndSet(currentHead, newHead) );
	}

	/**
	 * 出栈
	 */
	E pop() {
		Node<E> currentHead = null;
		Node<E> newHead = null;
		do {
			currentHead = atomicHead.get();	//当前头元素
			if(currentHead != null){
				newHead = currentHead.next;	//新的头元素
			} else
				return null;
			
		} while( !atomicHead.compareAndSet(currentHead, newHead) );	//重设头元素
		
		return currentHead.element;
	}

	static class Node<E> {
		E element; // 存储的值
		Node<E> next; // 下一个指针

		public Node(E ele) { // 构造方法
			this.element = ele;
		}
	}
	
	public static void main(String[] args){
		ConcurrentStack<String> stack = new ConcurrentStack<String>();
		System.out.println("pop: " + stack.pop());
		
		stack.push("zhu");	//先进
		stack.push("runjia");
		stack.push("9");	//后进先出
		
		System.out.println("pop: " + stack.pop());
	}

}
