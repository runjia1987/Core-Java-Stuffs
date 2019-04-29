package org.jackJew.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * linkedList copy with random pointer
 * @author Jack
 */
public class LinkedListWithRandomPointer<E> {
	
	private Node<E> head;
	private final static int count = 50000;
	
	/**
	 * based on array and ArrayList
	 */
	public Node<E> deepCopy() {
		// create mapping
		List<Node<E>> list = new ArrayList<>();
		Node<E> previous = new Node<>(head.value);
		List<Node<E>> newNodes = new ArrayList<>(count);
		int i = 0;
		for(Node<E> node = head; node != null; node = node.next, i++) {
			list.add(node);
			if(i > 0) {
				previous.next = new Node<>(node.value);
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
				array[i] = list.indexOf(randomPointer);
			}
		}
		list.clear();

		i = 0;
		Node<E> newHead = newNodes.get(0);
		// set random pointer
		for(Node<E> node = newHead; node != null; node = node.next, i++) {
			int pos = array[i];
			if(pos != -1) {
				node.rand = newNodes.get(pos);
			}
		}
		return newHead;
	}

	/**
	 * based on LinkedList
	 */
	public Node<E> deepCopyInPlace() {
		Node<E> current = head, copy = null, temp;
		// copy in place
		while (current != null) {
			copy = new Node<>(current.value);
			temp = current.next;
			current.next = copy;
			copy.next = temp;
			current = temp;
		}
		// set rand ptr in place
		current = head;
		while (current != null) {
			temp = current.next;
			if (current.rand != null) {
				temp.rand = current.rand.next;
			}
			current = temp.next;
		}
		// split between
		current = head;
		Node<E> newHead = null;
		while (current != null) {
			temp = current.next;
			if (newHead == null) {
				newHead = temp;
			}
			current.next = temp.next;  // maintain the old linkedlist
			current = temp.next;
			if (current != null) {
				temp.next = current.next;
			}
		}
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
			if (head == null) {
				head = current;
			}
			// next pointer
			current.next = nodes.get(i + 1);
			// random pointer
			current.rand = nodes.get(rand.nextInt(size - 1));
			i++;
		}
	}	

	public static void main(String[] args) {
		int printNum = 10, index = 0;
		LinkedListWithRandomPointer<Integer> list = new LinkedListWithRandomPointer<>();
		Integer[] array = createRandomArray();
		list.create(array);

		long startTime = System.currentTimeMillis();
		// deepCopy
		Node<Integer> newHead = list.deepCopy();
		System.out.println("\ntime cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		for(Node<Integer> node = newHead; node != null; node = node.next) {
			if (++index > printNum) break;
			System.out.print(node + ", ");
		}

		startTime = System.currentTimeMillis();
		// deepCopy in place (faster by 20+ times)
		newHead = list.deepCopyInPlace();
		System.out.println("\ntime cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		index = 0;
		for(Node<Integer> node = newHead; node != null; node = node.next) {
			if (++index > printNum) break;
			System.out.print(node + ", ");
		}

		// print
		System.out.println();
		index = 0;
		for(Node<Integer> node = list.head; node != null; node = node.next) {
			if (++index > printNum) break;
			System.out.print(node + ", ");
		}
		System.out.println();

	}
	
	private static Integer[] createRandomArray() {
		Random rand = new Random();
		Integer[] array = new Integer[count];
		for(int i = 0; i < count; i++) {
			array[i] = rand.nextInt(10000);
		}
		return array;
	}
}

class Node<E> {
	E value;
	Node<E> next;
	Node<E> rand;

	public Node(E value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
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