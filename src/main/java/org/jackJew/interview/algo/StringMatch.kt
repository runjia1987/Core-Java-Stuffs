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
    for (index in m - 1 downTo 0) {
      val ch = model[index].toInt()
      if (shift[ch] == -1) shift[ch] = index // 最右出现的下标
    }
    var i = 0  // 文本串开始匹配的下标
    var j = 0  // 模式串下标
    while (i <= string.length - m) {
      if (string[i + j] == model[j]) {
        j++
        if (j == m) return i  // 获得
      } else if (i + m == string.length) {
        return -1
      } else {
        i += (m - shift[string[i + m].toInt()]) // i+m 为文本串中m位后的失配元素, 执行shift判定
        j = 0
      }
    }
    return -1
  }

  /**
   * 计算最长公共前缀.
   */
  private fun lps(model: String): IntArray {
    val lps = IntArray(model.length) { 0 } // 初始化为0 [longest prefix suffix]
    var j = 1    // the loop calculates lps[i]
    var len = 0  // length of the previous longest prefix suffix
    while(j < model.length) {
      if (model[j] == model[len]) {
        len++
        lps[j] = len
        j++
      } else {
        // This is tricky. Consider the example.
        // AAACAAAA and i = 7. The idea is similar to search step.
        if (len > 0) len = lps[len - 1]
        else {
          lps[j] = 0
          j++
        }
      }
    }
    return lps
  }

  fun kmp(string: String, model: String): Int {
    // 从model模式串构造公共长度数组
    val m = model.length
    val lps = lps(model)
    var i = 0  // 文本串开始匹配的下标
    var j = 0      // 模式串下标
    while (i <= string.length - m) {
      if (string[i + j] == model[j]) {
        j++
        if (j == m) return i // 获得
      } else if (j == 0) {
        i++
      } else {
        i += (j - lps[j - 1])
        j = lps[j - 1]
      }
    }
    return -1
  }
}

fun main() {
  var string = "abcdefg abcdabcdf"
  var model = "abcdf"

  val match = StringMatch()
  assert(match.sunday(string, model) == 12)
  assert(match.kmp(string, model) == 12)

  string = "abcdefg abcdbbcdf"
  assert(match.sunday(string, model) == -1)
  assert(match.kmp(string, model) == -1)

  model = "bb"
  assert(match.sunday(string, model) == 12)
  assert(match.kmp(string, model) == 12)
}