package org.jackJew.algorithm;

import java.util.Stack;

/**
 * tree reverse non-recursive
 * @author Jack
 *
 */
public class TreeReverse {
	
	private TreeNode<String> root;
	
	class TreeNode<T> {
		TreeNode<T> left, right;
		T value;
		
		public TreeNode(T value){
			this.value = value;
		}
	}
	
	public void reverse() {
		Stack<TreeNode<String>> stack = new Stack<TreeNode<String>>();
		stack.push(root);
		while(stack.size() > 0) {
			TreeNode<String> parent = stack.pop();
			TreeNode<String> leftNode = parent.left;
			TreeNode<String> rightNode = parent.right;
			// reverse
			parent.left = rightNode;
			parent.right = leftNode;
			if (rightNode != null) stack.push(rightNode);
			if (leftNode != null) stack.push(leftNode);
		}
	}

}
