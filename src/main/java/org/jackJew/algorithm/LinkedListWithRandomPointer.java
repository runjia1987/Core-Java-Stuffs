package org.jackJew.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * linkedList copy with random pointer
 * @author Jack
 */
public class LinkedListWithRandomPointer<E> {
	
	private Node<E> head;
	private int count;
	
	static class Node<E> {		
		E value;
		Node<E> next;
		Node<E> rand;
		
		public Node(E value) {
			this.value = value;
		}
		
		public String toString() {
			StringBuilder builder = new StringBuilder("{" + value.toString());
			if(next != null) {
				builder.append(",").append(next.value);
			}
			if(rand != null) {
				builder.append(",").append(rand.value);
			}
			return builder.append("}").toString();
		}
	}
	
	/**
	 * based on array and map
	 */
	public Node<E> deepCopy() {
		if(head == null) {
			return null;
		}
		// create mapping
		Map<Node<E>, Integer> map = new HashMap<>();
		Node<E> previous = new Node<E>(head.value);
		List<Node<E>> newNodes = new ArrayList<>(count);
		int i = 0;
		for(Node<E> node = head; node != null; node = node.next, i++) {
			map.put(node, i);
			if(i > 0) {
				previous.next = new Node<E>(node.value);
				previous = previous.next;				
			}
			newNodes.add(previous);
		}
		int[] array = new int[count];
		i = 0;
		for(Node<E> node = head; node != null; node = node.next, i++) {
			Node<E> randomPointer = node.rand;
			if(randomPointer == null) {
				array[i] = -1;
			} else {
				Integer pos = map.get(randomPointer);
				array[i] = (pos == null ? -1 : pos);
			}
		}
		map.clear();

		i = 0;
		Node<E> newHead = newNodes.get(0);
		// set random pointer
		for(Node<E> node = newHead; node != null; node = node.next, i++) {
			int pos = array[i];
			if(pos != -1) {
				node.rand = newNodes.get(pos);
			}
		}
		array = null;
		return newHead;
	}
	
	/**
	 * create LinkedList from array
	 */
	public void create(E[] array) {
		if(array == null || array.length == 0) {
			return;
		}
		List<Node<E>> nodes = new ArrayList<>(array.length);
		for(E value : array) {
			nodes.add(new Node<>(value));
		}
		
		int i  = 0, size = nodes.size();
		Random rand = new Random();
		while(i < size - 1) {
			Node<E> current = nodes.get(i);
			// next pointer
			current.next = nodes.get(i + 1);
			// random pointer
			current.rand = nodes.get(rand.nextInt(size - 1));
			i++;
		}
		head = nodes.get(0);
		count = nodes.size();
	}	

	public static void main(String[] args) {
		LinkedListWithRandomPointer<Integer> list = new LinkedListWithRandomPointer<>();
		Integer[] array = {800, 900, 10000, 100, 200, 300, 400, 500, 600, 700, };
		list.create(array);
		
		// print
		for(Node<Integer> node = list.head; node != null; node = node.next) {
			System.out.print(node + ", ");
		}
		System.out.println();
		// deepCopy
		Node<Integer> newHead = list.deepCopy();
		for(Node<Integer> node = newHead; node != null; node = node.next) {
			System.out.print(node + ", ");
		}
	}

}
