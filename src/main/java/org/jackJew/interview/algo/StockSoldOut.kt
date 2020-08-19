package org.jackJew.interview.algo

/**
 * 股票最多获利卖出时机
 */
fun soldOut(array: IntArray): Int {
  var profit = 0
  var lowest = array[0] + 1
  for(index in array.indices) {
    val diff = array[index] - lowest
    profit = if (diff > profit ) diff else profit
    if (lowest > array[index]) {
      lowest = array[index]
    }
  }
  return profit
}

fun main() {
  println(soldOut(intArrayOf(8, 4, 6, 7, 3, 4, 5)))
}