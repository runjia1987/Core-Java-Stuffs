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
	public void setUpTree() {
		int i = 0;
		final int maxIndex = array.length - 1;
		Queue<Node> nodeList = new LinkedList<>();  // for space efficiency, use LinkedList, we use offer & poll
		while( true ) {
			if ( root == null ){
				root = new Node(array[i]);
				nodeList.offer(root);
			} else {
				Node node = nodeList.poll();
				System.out.println(i);
				int leftIndex = (i << 1) + 1, rightIndex = leftIndex + 1;
				if( leftIndex > maxIndex ) {
					break;
				}
				node.left = new Node(array[leftIndex]);
				nodeList.offer(node.left);

				if (rightIndex <= maxIndex) {
					node.right = new Node(array[rightIndex]);
					nodeList.offer(node.right);
				} else {
					break;
				}
				i++;
			}
		}
	}
	
	/**
	 * traverse in depth-first, by Stack
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

	class TempNode {
		Node node;
		boolean reput;

		TempNode(Node node) {
			this.node = node;
		}

		TempNode(Node node, boolean reput) {
			this.node = node;
			this.reput = reput;
		}
	}

	/**
	 * traverse in middle-root order
	 */
	public void middleOrderTraverse() {
		Stack<TempNode> stack = new Stack<>();
		stack.push(new TempNode(root));
		TempNode curr;
		boolean printParent = false;
		while (! stack.isEmpty()) {
			curr = stack.pop();
			if (printParent || curr.reput) {
				System.out.println(curr.node.value);
				printParent = false; // reset to false
			} else {
				if (curr.node.right == null && curr.node.left == null) {
					System.out.println(curr.node.value);
					printParent = true;
				} else {
					if (curr.node.right != null)   // first push right-branch
						stack.push(new TempNode(curr.node.right));

					stack.push(new TempNode(curr.node, true)); //  re-put current node

					if (curr.node.left != null)     // then push left-branch
						stack.push(new TempNode(curr.node.left));
				}
			}
		}
	}

	/**
	 * traverse in post-root order
	 */
	public void postOrderTraverse() {
		Stack<TempNode> stack = new Stack<>();
		stack.push(new TempNode(root));
		TempNode curr;
		boolean printRight = false;
		while (! stack.isEmpty()) {
			curr = stack.pop();
			if (printRight || curr.reput) {
				System.out.println(curr.node.value);
				printRight = false; // reset to false
			} else {
				if (curr.node.right == null && curr.node.left == null) {
					System.out.println(curr.node.value);
					printRight = true;
				} else {
					stack.push(new TempNode(curr.node, true)); //  re-put current node

					if (curr.node.right != null)   // push right-branch
						stack.push(new TempNode(curr.node.right));

					if (curr.node.left != null)     // then push left-branch
						stack.push(new TempNode(curr.node.left));

				}
			}
		}
	}
	
	/**
	 * traverse in width-first, by Queue
	 */
	public void breadthTraverse(){
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while( ! queue.isEmpty() ) {
			Node node = queue.poll();
			System.out.println(node.value);
			
			if(node.left != null) 
				queue.offer(node.left);
			
			if(node.right != null)
				queue.offer(node.right);
		}
	}

	/**
	 * Get min depth. level N count < 1 pow (N-1)
	 */
	public int getMinDepth() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int level = 1;
		while ( ! queue.isEmpty() ) {
			int count = queue.size(), i = 0;
			while (i++ < count) {
				Node node = queue.poll();
				if(node.left != null) {
					queue.offer(node.left);
				}
				if(node.right != null) {
					queue.offer(node.right);
				}
			}
			level++;
		}
		return level;
	}

	/**
	 * Get max depth.
	 */
	public int getMaxDepth() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int level = 1;
		while ( ! queue.isEmpty() ) {
			if (queue.size() != 1 << (level -1)) {
				break; // get it.
			}
			int count = queue.size(), i = 0;
			while (i++ < count) {
				Node node = queue.poll();
				if(node.left != null) {
					queue.offer(node.left);
				}
				if(node.right != null) {
					queue.offer(node.right);
				}
			}
			level++;
		}
		return level;
	}
	
	/**
	 * create sibling linkes for all nodes in the existing tree,
	 * based on breadth traversing
	 */
	public void createSiblingLinks() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while ( ! queue.isEmpty() ) {
			Queue<Node> siblingNodes = new LinkedList<>();  // hold the siblings nodes of the same layer

			int count = queue.size(), i = 0;
			while (i++ < count) {
				Node node = queue.poll();
				if(node.left != null) {
					queue.offer(node.left);
					siblingNodes.offer(node.left);
				}
				if(node.right != null) {
					queue.offer(node.right);
					siblingNodes.offer(node.right);
				}
			}
			siblingLink(siblingNodes);  // the bottom layer if any
			siblingNodes.clear();
		}
	}
	
	/**
	 * link the elements from the most-left to the most-right on the same layer
	 * @param siblingNodes
	 */
	private void siblingLink(Queue<Node> siblingNodes){
		Iterator<Node> itr = siblingNodes.iterator();
		
		for(Node n , previous = null; itr.hasNext(); previous = n) {
			n = itr.next();
			if ( previous != null ) {
				previous.nextSibling = n;
				System.out.print(previous + " sibling is " + n + ", ");
			}
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
		System.out.println("complete to setup a binary tree\n");		
		
		System.out.println("start to breadthTraverse a binary tree");		
		tt.breadthTraverse();
		System.out.println("complete to breadthTraverse a binary tree\n");
		
		System.out.println("start to depthTraverse a binary tree");
		tt.depthTraverse();
		System.out.println("complete to depthTraverse a binary tree\n");
		
		tt.createSiblingLinks();

		System.out.println("start to middleOrderTraverse a binary tree\n");
		tt.middleOrderTraverse();

		System.out.println("start to postOrderTraverse a binary tree\n");
		tt.postOrderTraverse();

		System.out.println("\nGot min length of tree is " + tt.getMinDepth());

		System.out.println("\nGot max length of tree is " + tt.getMaxDepth());
	}
	
	public void setArray(int[] array) {
		this.array = array;
	}

	public Node getRoot() {
		return root;
	}

}
