package org.jackJew.algorithm;

/**
 * assume this binary tree is a complete binary tree, which has two kinds of storage: <br>
 * 1) array-based, node at index n has two children nodes: 2n+1 & 2n+2, just like a heap;
 * 2) left | child pointer-based, just like java.util.TreeMap
 * @author Jack
 *
 */
public class BinaryTreeSort {
	
	/**
	 * create a a sorted binary tree, LDR incremental
	 */
	public int[] createSortedBinaryTree(int maxSize){
		// TODO
		return null;
	}
	
	public boolean checkIfSortedTree(int[] treeArray){
		// TODO
		// logic:
		// for each node with index n, has two children(2n+1,2n+2) or less, <br>
		// and the parent is calculated as below:
		// if n % 2 == 0, this node is right child, parent is n/2 - 1; 
		// else, this node is left child, parent is n/2;

		// Make sure: left <= this node <= right;
		// and all children nodes should be less than parent(if this node is left child),
		// or greater than parent(if this node is right child)
		
		// this algorithm will be much more efficient than pointer-based TreeMap,
		// with precise O(n) time complexity;
		// while pointer-based TreeMap takes more than O(n) time complexity for certain.
		
		return false;
	}
	
	public void createSortedTreeMap(int maxSize) {
		// create root
		root = new Entry(100);
		
	}
	
	public boolean checkIfSortedMap(int[] treeArray){
		// pointer-based TreeMap takes more than O(n) time complexity for certain.
		return false;
	}
	
	private Entry root;
	
	/**
	 * different from TreeMap, this is not a Red-black tree, just as an example
	 * @author Jack
	 *
	 */
	private class Entry {
		int value;
		Entry left;
		Entry chilld;
		
		public Entry(int value){
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		
	}

}
