package org.jackJew.interview.algo;

/**
 * 前缀字典树.
 */
public class TrieTree {

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
    System.out.println(new TrieTree().lps("abab"));
  }
}
