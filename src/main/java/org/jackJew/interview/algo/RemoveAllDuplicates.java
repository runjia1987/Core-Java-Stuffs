package org.jackJew.interview.algo;

public class RemoveAllDuplicates {
    private Node head;

    static class Node {
        int val;
        Node next;
    }

    // 1111 -> null
    // 11122 -> null
    // 1112 -> 2
    // 122334 -> 14
    public void removeDuplicated() {
        if (head == null) return;
        Node cur = head, prev = head, right = cur.next;
        while (true) {
            if (right == null) {
                if (cur == head && cur.next != null && cur.next.val == cur.val) {
                    head = null;
                }
                break;
            }
            if (prev.next.val == right.val) {
                right = right.next;
            } else {
                prev.next = right;
                cur = right;
                right = right.next;
                if (cur.val != right.val) {
                    prev = cur;
                }
            }
        }
    }
}
