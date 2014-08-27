package org.jackJew.algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Traverse a binary tree
 * @author Jack
 *
 */
public class TreeTraversing {
	
	private int[] array;

	private Node root;
	
	class Node {
		private int value;
		private Node left;
		private Node right;
		private Node nextSibling;
		
		public Node(){
			
		}
		
		public Node(int value){
			this.value = value;
		}
		
		@Override
		public String toString(){
			return String.valueOf(this.value);
		}
	}
	
	/**
	 * contruct a tree
	 */
	public void setUpTree(){
		int i = 0;
		int maxIndex = array.length - 1;
		LinkedList<Node> nodeList = new LinkedList<Node>();  // for space efficiency, use LinkedList, we use add & delete
		while( true ) {
			Node node = null;
			if ( root == null ){
				root = new Node(array[i]);
				nodeList.add(root);
				node = root;
			} else {
				node = nodeList.getFirst();
			}
			System.out.println(i);
			int leftIndex = (i << 1) + 1, rightIndex = leftIndex + 1;
			if( leftIndex > maxIndex ) {
				break;
			}
			node.left = new Node(array[leftIndex]);
			nodeList.add(node.left);
			
			if (rightIndex <= maxIndex) {
				node.right = new Node(array[rightIndex]);
				nodeList.add(node.right);
			} else {
				break;
			}
			nodeList.removeFirst();   // delete the first node in list
			
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
			
			if(node.right != null)   // first push right-branch
				stack.push(node.right);
			
			if(node.left != null)	 // then push left-branch
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
	 * create sibling linkes for all nodes in the existing tree, <br>
	 * based on breadth traversing
	 */
	public void createSiblingLinks() {
		Queue<Node> queue = new LinkedList<Node>();
		List<Node> siblingNodes = new LinkedList<Node>();  // hold the siblings nodes
		queue.add(root);
		int times = 0, expectedPollCount = 1;
		while ( ! queue.isEmpty() ){
			Node node = queue.poll();
			times++;			
			
			if(node.left != null) {
				queue.add(node.left);
				siblingNodes.add(node.left);
			}

			if(node.right != null) {
				queue.add(node.right);
				siblingNodes.add(node.right);
			}
			
			if ( times == expectedPollCount) {
				siblingLink(siblingNodes);
				expectedPollCount <<= 1;  // double it
				times = 0;  // reset it
				siblingNodes.clear();  // empty the temp queue
			}
		}
		siblingLink(siblingNodes);
	}
	
	/**
	 * link the elements from the most-left to the most-right
	 * @param siblingNodes
	 */
	private void siblingLink(List<Node> siblingNodes){
		Iterator<Node> itr = siblingNodes.iterator();
		Node n, previous = null;
		while(itr.hasNext()) {
			n = itr.next();
			if ( previous != null ) {
				previous.nextSibling = n;
				System.out.print(previous + " sibling is " + n + ", ");
			}
			previous = n;			
		}
		System.out.println();
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
		
		tt.createSiblingLinks();
		
	}
	
	public void setArray(int[] array) {
		this.array = array;
	}

	public Node getRoot() {
		return root;
	}

}
