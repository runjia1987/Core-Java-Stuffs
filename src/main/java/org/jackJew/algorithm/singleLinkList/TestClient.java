package org.jackJew.algorithm.singleLinkList;

public class TestClient {

	/**
	 * 测试单链表数据结构
	 */
	public static void main(String[] args) {
		LinkList list1 = LinkList.createFromArray("a", "b", "c", "d", "e", "f", "g", "h");
		list1.print();
		
		list1.insertBeforeHead("1234");
		list1.insertAfterTail("runjia");
		System.out.println("添加节点后：");
		list1.print();
		
		LinkNode node = list1.findAtIndex(5);
		System.out.println("查找到值：" + node.content);
		
		list1.removeAtIndex(3);
		
		list1.print();
		
		// reverse the list		
		list1.reverse();
		System.out.println("after reverse:");
		list1.print();
	}

}
