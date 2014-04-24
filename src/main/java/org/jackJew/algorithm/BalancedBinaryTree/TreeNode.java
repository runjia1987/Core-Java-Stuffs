package org.jackJew.algorithm.BalancedBinaryTree;

/**
 * 二叉树节点, <br>
 * 		前序遍历: 根节点 - 左节点 - 右节点<br>
 * 		中序遍历: 左节点 - 根节点 - 右节点<br>
 * 		后序遍历: 左节点 - 右节点 - 根节点<br>
 * <br>
 * 有向图的遍历: <br>
 * 		深度优先遍历（使用栈stack）<br>
 * 		广度优先遍历（使用队列queue）
 * @author zhurunjia
 */
public class TreeNode {
	
	/**
	 * use package access, 包访问权限
	 */
	int value;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data) {
		this.value = data;
	}
	
	public TreeNode() { }

	String fullValue(){
		return (this.value + ", 左节点: " + this.left + ", 右节点: " + this.right);
	}
	
	public String toString(){
		return String.valueOf(this.value);
	}

}
