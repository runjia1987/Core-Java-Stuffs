package org.jackJew.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversing {
	
	private int[] array;

	private Node root;
	
	class Node {
		private int value;
		private Node left;
		private Node right;
		
		public Node(){
			
		}
		
		public Node(int value){
			this.value = value;
		}
	}
	
	/**
	 * contruct a tree
	 */
	public void setUpTree(){
		int i = 0;
		int threshold = array.length / 2;
		Node[] nodeList = new Node[threshold];  // for space efficiency, half of them
		while( i < array.length / 2 ) {
			Node node = null;
			if ( root == null ){
				root = new Node(array[i]);
				nodeList[0] = root;
				node = root;
			} else {
				node = nodeList[i];
			}
			System.out.println(i);
			int leftIndex = (i << 1) + 1, rightIndex = leftIndex + 1;
			node.left = new Node(array[leftIndex]);
			if(leftIndex < threshold){
				nodeList[leftIndex] = node.left;
			}
			
			if (rightIndex < array.length) {
				node.right = new Node(array[rightIndex]);
				if(rightIndex < threshold) {
					nodeList[rightIndex] = node.right;
				}
			}
			
			i++;
		}
	}
	
	/**
	 * traverse in depth-first
	 */
	public void depthTraverse(){
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		
		while ( ! stack.isEmpty() ) {
			Node node = stack.pop();
			System.out.println(node.value);
			
			if(node.right != null)
				stack.push(node.right);
			
			if(node.left != null)
				stack.push(node.left);
		}
	}
	
	/**
	 * traverse in width-first
	 */
	public void breadthTraverse(){
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while( ! queue.isEmpty() ) {
			Node node = queue.poll();
			System.out.println(node.value);
			
			if(node.left != null) 
				queue.add(node.left);
			
			if(node.right != null)
				queue.add(node.right);
		}
	}
	
	/**
	 * testcase
	 */
	public static void main(String[] args){
		int[] array = {100,90,43,456,34,0,46,9,677,1001,78,321,2};
		TreeTraversing tt = new TreeTraversing();
		tt.setArray(array);
		
		System.out.println("start to setup a binary tree");		
		tt.setUpTree();
		System.out.println("complete to setup a binary tree");		
		
		System.out.println("start to breadthTraverse a binary tree");		
		tt.breadthTraverse();
		System.out.println("complete to breadthTraverse a binary tree");
		
		System.out.println("start to depthTraverse a binary tree");
		tt.depthTraverse();
		System.out.println("complete to depthTraverse a binary tree");
		
	}
	
	public void setArray(int[] array) {
		this.array = array;
	}

	public Node getRoot() {
		return root;
	}

}
