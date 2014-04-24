package org.jackJew.algorithm.BalancedBinaryTree;

public class TestClient {

	/**
	 * 测试二叉排序树
	 */
	public static void main(String[] args) {
		
		int[] datas = {100, 6, 44, 56, 33, 22, 890, 134};
		
		BalancedTree tree = new BalancedTree();
		
		tree.createBalancedTree(datas);
		tree.searchNode(44);
		//tree.deleteNode(tree.getRoot(), 44);
		//tree.searchNode(44);
		
		tree.displayByTree(tree.getRoot());
		//tree.displayByASC();
		
	}

}
