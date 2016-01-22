package org.jackJew.algorithm.singleLinkList;

/**
 * link node 单链表节点
 * @author zhurunjia
 */
public class LinkNode {
	
	/**
	 * 内容
	 */
	String content;
	
	/**
	 * 下个节点
	 */
	LinkNode next;
	
	/**
	 * 构造器
	 */
	public LinkNode(String content){
		this.content = content;
	}
	
	public LinkNode(String content, LinkNode next){
		this.content = content;
		this.next = next;
	}

}
