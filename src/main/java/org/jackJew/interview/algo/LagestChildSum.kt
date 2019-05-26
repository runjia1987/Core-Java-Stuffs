package org.jackJew.interview.algo

//获取数组的最长子串
fun find(array: IntArray): Int {
  var maxSum = 0
  var thisSum = 0
  for (index in 0 until array.size) {
    thisSum += array[index]
    if (thisSum > maxSum)
      maxSum = thisSum
    else if (thisSum < 0) {
      thisSum = 0
    }
  }
  return maxSum
}

fun main() {
  println(find(intArrayOf(4, 5, 6, -10, -9, -3, 7, 8, 9)))
}
