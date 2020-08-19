package org.jackJew.classes;

public class Prime {

  static int getNextPrime(int s) {
    if (s == 1) return 2;
    if (s == 2) return 3;
    if (s == 3 || s == 4) return 5;
    while (s++ < Integer.MAX_VALUE) {
      int d = s % 6;
      if (d != 1 && d != 5) { // prime is 6n-1 or 6n + 1
        continue;
      }
      d = s < 25 ? s : (int) Math.sqrt(s);
      int i = 5;
      while (i <= d) {
        if (s == i || s == i + 2) return s;
        if (s % i == 0 || s % (i + 2) == 0) { //ignore 2m and 3m
          break;
        }
        i += 6;
      }
      if (i > d)
        return s;
    }
    throw new IllegalArgumentException("too large input " + s);
  }

  class Node<V> {
    V value;
    Node<V> next;
  }

  void remove(Node<Integer> cur) {
    while (cur != null) {
      Node<Integer> after = cur.next;
      cur.value = after.value;
      if (after.next == null) {
        cur.next = null;
        break;
      }
      cur = after;
    }
  }

}
