package org.jackJew.interview.algo;

/**
 * 前缀字典树.
 */
public class TrieTree {

  private final Node root = new Node('\u0000');

  static class Node {
    int prefix_num;
    Node[] children;
    char c;
    boolean isWord; // 当前节点是词

    public Node(char c) {
      this.c = c;
      children = new Node[0];
    }

    Node addChild(char c) {
      Node child = new Node(c);
      final int len = children.length;
      Node[] newArray = new Node[len + 1];
      if (len > 0) {  //expand
        System.arraycopy(children, 0, newArray, 0, len);
      }
      newArray[len] = child;
      children = newArray;
      return child;
    }
  }

  public void put(String str) {
    int index = 0;
    Node cur = root;
    boolean found;
    while(index < str.length()) {
      found = false;
      for (Node n : cur.children) {
        if (n.c == str.charAt(index)) {
          found = true;
          cur = n;
          break;
        }
      }
      if (!found) {
        cur = cur.addChild(str.charAt(index));
      }
      index++;
    }
    cur.isWord = true;
    cur.prefix_num++;
  }

  public int search(String str) {
    int index = 0;
    Node cur = root;
    boolean found;
    while(index < str.length()) {
      found = false;
      for (Node n : cur.children) {
        if (n.c == str.charAt(index)) {
          found = true;
          cur = n;
          break;
        }
      }
      if (!found) return 0;
      index++;
    }
    if (cur.isWord)
      return cur.prefix_num;
    return 0;
  }

  public int[] lps(String model) {
    final int length = model.length();
    int lps[] = new int[length];
    lps[0] = 0;
    int k = 1, len = 0;
    while (k < length) {
      if (model.charAt(k) == model.charAt(len)) {
        lps[k++] = ++len;
      } else {
        if (len > 0) len = lps[len - 1];
        else {
          lps[k++] = 0;
        }
      }
    }
    return lps;
  }

  public static void main(String[] args) {
    TrieTree trieTree = new TrieTree();
    trieTree.put("abc");
    trieTree.put("def");
    trieTree.put("bcd");
    System.out.println(trieTree.search("cd")); // 0
    trieTree.put("cd");
    System.out.println(trieTree.search("cd")); // 1
    trieTree.put("cd");
    System.out.println(trieTree.search("cd")); // 2

    System.out.println(trieTree.lps("abab"));
  }
}
