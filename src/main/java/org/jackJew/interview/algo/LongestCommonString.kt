package org.jackJew.interview.algo

// 最大字符串公共长度 LCS
class LongestCommonString {

  fun find(string1: String, string2: String): String {
    val m = string1.length
    val n = string2.length

    var maxLength = 0 // 最大公共长度
    var end = 0 // 最长公共子串的终止点
    val dp = Array(m) { IntArray(n) } // 二维数组
    for (i in 0 until n)  // 第一行 init
      dp[0][i] = if (string1[0] == string2[i]) 1 else 0
    for (i in 0 until m) // 第一列 init
      dp[i][0] = if (string2[0] == string1[i]) 1 else 0

    for (i in 1 until m)
      for (j in 1 until n) {
        if (string1[i] == string2[j]) {
          dp[i][j] = dp[i - 1][j - 1] + 1
          if (dp[i][j] > maxLength) {
            maxLength = dp[i][j] //记录
            end = i
          }
        }
        else dp[i][j] = 0
      }

    return string1.substring(end - maxLength + 1, end + 1)
  }
}

fun main() {
  println(LongestCommonString().find("1ab432", "cab12345"))
}