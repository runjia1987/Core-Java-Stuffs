package org.jackJew.concurrent;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 基于synchronized关键字的线程安全链表实现, block模式
 * @author zhurunjia
 */
public class SynchronizedLinkList<E> {
	
	private LinkedList<E> linkList = new LinkedList<E>();
	
	//JDK LinkedList基于双向链表实现, java.util.LinkedList.Entry<E>
	/**
	 * LinkedList源码：
	 * Entry[] table;
	 * 内部类: static class Entry {
	 * 		final K key;
	 * 		V value;
	 * 		final int hash;
	 * 		Entry next;	 //next指针形成一个单链表
	 * 	 }
	 * <br> 注意JDK 1.7 将类Entry 重命名为 Node, 更符合语义.
	 */	
	
	public synchronized void put(E object){
		linkList.add(object);
		
		if(linkList.size() > 0)
			notifyAll();
	}
	
	public synchronized E take() throws InterruptedException {
		//Collections.synchronizedList(list);
		
		while(linkList.size() == 0)
			wait();
		
		return linkList.remove(0);
	}
	
	public static void main(String... args){
		SynchronizedLinkList<String> sq = new SynchronizedLinkList<String>();
		try {
			String str = sq.take();   //the thread holds until something is available
			System.out.println(str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
