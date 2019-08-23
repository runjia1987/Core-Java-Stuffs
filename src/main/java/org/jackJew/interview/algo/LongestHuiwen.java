package org.jackJew.interview.algo;

import org.junit.Assert;

public class LongestHuiwen {

  /**
   * 最长回文串.
   */
  public static String compute(String str) {
    final int length = str.length();
    final boolean[][] dp = new boolean[length][length];  //表示从j 到 i的子串是否为回文串
    String res = "";  // 当前最大回文串
    for (int i = 0; i < length; i++)
      for (int j = 0; j<= i; j++) {
        dp[j][i] = str.charAt(i) == str.charAt(j) &&
            (i - j <= 2 || dp[j+1][i-1]);
        if (dp[j][i]) {
          if (res.length() < (i - j + 1)) { // 新的最大子串
            res = str.substring(j, i + 1);
          }
        }
      }
    return res;
  }

  public static void main(String[] args) {
    String str = "123aaba3";
    Assert.assertEquals(compute(str), "aba");

    str = "123aa32";
    Assert.assertEquals(compute(str), "23aa32");
  }
}
