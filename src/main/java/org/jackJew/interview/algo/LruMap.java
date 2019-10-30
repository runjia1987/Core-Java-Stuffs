package org.jackJew.interview.algo;

import java.util.HashMap;

public class LruMap<K, V> extends HashMap<K, V> {

  private Node head;
  private Node tail;

  static class Node<K> {
    private Node before;
    private Node next;
    private K key;

    public Node(K key) {
      this.key = key;
    }
  }

  // or extends LinkedHashMap
}
