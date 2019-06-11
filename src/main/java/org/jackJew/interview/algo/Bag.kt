package org.jackJew.interview.algo;

// 背包算法(01)
class Bag {
  val weight = listOf(0, 2, 3, 4, 5)
  val values = listOf(0, 2, 3, 4, 5)

  val maxCapacity = 12 // 在<=最大容量情况下使总价值最大

  fun calc(): Array<IntArray> {
    val m = weight.size
    val dp = Array(m) { IntArray(maxCapacity + 1) }  // value 矩阵
    for (w in 0 .. maxCapacity)  // 0..maxCapacity 外层是背包的容量
      for (p in 0 until m) {  // 背包确定容量时, 依次拿物品来装
        if (w == 0 || p == 0) dp[p][w] = 0 // init
        else if (w < weight[p]) dp[p][w] = dp[p - 1][w]  // overweight, use previous
        else {
          // if 选择不放新物品, use previous
          val a = dp[p - 1][w]
          // if 选择放新物品
          val b = dp[p - 1][w - weight[p]] + values[p]
          dp[p][w] = Math.max(a, b)
        }
      }

    return dp
  }
}

fun main() {
  val bag = Bag()
  bag.calc().apply {
    forEach { println(it.joinToString(", ")) }
    println(this[bag.values.size - 1][bag.maxCapacity])  // max value
  }
}
