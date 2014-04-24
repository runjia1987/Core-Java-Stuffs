package org.jackJew.algorithm.singleLinkList;

/**
 * 单链表
 * @author zhurunjia
 */
public final class LinkList {

	/**
	 * 头节点
	 */
	LinkNode head;

	public LinkList() {
	}

	public LinkList(String content) {
		this.head = new LinkNode(content);
	}

	/**
	 * 从数组构建一个新链表, 静态工具方法
	 */
	public static LinkList createFromArray(String... contents) {
		if (contents != null && contents.length > 0) {
			LinkList list = new LinkList();
			list.head = new LinkNode(contents[0]);
			int i = 1;
			
			LinkNode node = list.head;
			while (i < contents.length) {
				node.next = new LinkNode(contents[i++]);
				node = node.next;
			}

			return list;
		} else
			return null;
	}

	/**
	 * 在头节点前插入
	 */
	public void insertBeforeHead(String content) {
		if (this.head == null) { // 当前链表为空
			this.head = new LinkNode(content);
		} else {
			LinkNode node = this.head;
			this.head = new LinkNode(content, node); // 赋值
		}
	}

	/**
	 * 在链表尾部插入
	 */
	public void insertAfterTail(String content) {
		LinkNode node = this.head;
		while (node.next != null)
			node = node.next;

		node.next = new LinkNode(content);
	}

	/**
	 * 在指定位置增加一个节点
	 */
	public void insertAtIndex(String content, int index) {
		if (index == 0) {
			
			insertBeforeHead(content);
			
		} else {
			LinkNode node = this.head;
			int i = 0;
			while (i++ < index - 1)
				node = node.next;

			LinkNode newNode = new LinkNode(content, node.next);
			node.next = newNode;
		}
	}

	public LinkNode findAtIndex(int index) {
		int i = 0;
		LinkNode node = this.head;
		while (i++ < index)
			node = node.next;

		return node;
	}

	public void removeAtIndex(int index) {
		int i = 0;
		LinkNode node = this.head;
		while (i++ < index - 1)
			node = node.next;

		node.next = node.next.next;
	}

	public void print() {
		LinkNode node = this.head;
		System.out.println();

		while (node != null) {
			System.out.print("节点：值" + node.content + "\t");
			node = node.next;
		}

		System.out.println();
	}

}
