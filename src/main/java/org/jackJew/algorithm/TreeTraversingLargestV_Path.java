package org.jackJew.algorithm;

import java.util.Stack;

/**
 * from codility.com
 * 
 * @author Jack
 *
 */
public class TreeTraversingLargestV_Path {

	private class Tree {
		private int x;
		private Tree l;
		private Tree r;

		public Tree(int x, Tree l, Tree r) {
			this.x = x;
			this.l = l;
			this.r = r;
		}
	}

	/**
	 * use depth-first traversing
	 */
	public int solution(Tree T) {
		// write your code in Java SE 8

		int number = 1;
		Stack<Tree> stack = new Stack<Tree>();

		if (T.l == null && T.r == null)
			return number;

		Tree parent = null;
		stack.push(T);
		while (!stack.isEmpty()) {

			parent = stack.pop();
			Tree child = null;
			if (parent.r != null) {
				child = parent.r;
				if (child.x >= parent.x) {
					number++;
				} else { // set to bigger value
					child.x = parent.x;
				}
				stack.push(child);
			}
			if (parent.l != null) {
				child = parent.l;
				if (child.x >= parent.x) {
					number++;
				} else { // set to bigger value
					child.x = parent.x;
				}
				stack.push(child);
			}
		}
		return number;
	}

	/**
	 * test case
	 */
	public static void main(String[] args) {
		TreeTraversingLargestV_Path ttlp = new TreeTraversingLargestV_Path();
		Tree root = ttlp.new Tree(10, null, null);
		root.l = ttlp.new Tree(12, null, null);
		root.r = ttlp.new Tree(9, null, null);
		Tree subt1 = ttlp.new Tree(20, null, null);
		Tree subt2 = ttlp.new Tree(100, null, null);
		root.r.l = subt1;
		root.r.r = subt2;

		int result = ttlp.solution(root);
		System.out.println(result); // as expected, result is 4
	}

}
