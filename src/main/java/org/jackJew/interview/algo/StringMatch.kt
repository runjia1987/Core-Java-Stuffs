package org.jackJew.interview.algo

// 字符串模式匹配算法：
// 1. 暴力匹配 类JDK 6模式, 适合各类语言文本, O(m * n);
// 2. KMP算法，基于最大前后缀公共长度或next[], 文本串总是前进. O(m + n)时间复杂度;
//             失配时右移位数:  已匹配字符数 - 失配字符的上一个字符对应的公共长度;
// 3. BM算法，基于坏字符与好后缀规则, 时间复杂度类似KMP;
// 4. Sunday算法, 模式串长度(m), 文本串右移位数: m -shift[]: shift对于无匹配的为-1, 有匹配的为最右出现的下标;
//                右移跨度大, 效率最快, 缺点仅适用于ASCII字符(shift数组空间考虑).
class StringMatch {

  fun sunday(string: String, model: String): Int {
    val m = model.length
    val shift = IntArray(128) { -1 } // 初始化为-1
    for (index in m - 1 .. 0) {
      val ch = model[index].toInt()
      if (shift[ch] == -1) shift[ch] = index // 最右出现的下标
    }
    var i = 0  // 文本串开始匹配的下标
    var j = 0  // 模式串下标
    while (i <= string.length - m) {
      if (string[i + j] == model[j]) {
        j++
        if (j == m) return i  // 获得
      } else
        i += (m - shift[string[i + m].toInt()]) // i+m 为文本串中m位后的失配元素, 执行shift判定
    }
    return -1
  }

  fun kmp(string: String, model: String): Int {
    // 从model模式串构造公共长度数组
    val m = model.length
    val s = IntArray(m) { 0 } // 初始化为0
    s[0] = 0
    var j = 0
    var k = -1
    while (j < m) {
      if (k == -1 || model[j] == model[k]) {
        j++
        k++
        s[j] = k
      } else
        k = s[k]
    }

    var i = 0  // 文本串开始匹配的下标
    j = 0      // 模式串下标
    while (i <= string.length - m) {
      if (string[i + j] == model[j]) {
        j++
        if (j == m) return i // 获得
      } else if (j == 0) {
        i++
      } else {
        i += (j - s[j - 1])
      }
    }
    return -1
  }
}

fun main() {
  val string = "abcdefg abcdabcdf"
  val model = "abcdf"

  val match = StringMatch()
  println(match.sunday(string, model))
  println(match.kmp(string, model))
}