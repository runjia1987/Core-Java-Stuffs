package org.jackJew.interview.algo

//获取数组的最长累加子串
fun addSum(array: IntArray): Int {
  var maxSum = array[0]
  var curSum = array[0]
  for (v in 1 until array.size) {
    curSum = Math.max(curSum + array[v], array[v])
    if (curSum > maxSum)
      maxSum = curSum
  }
  return maxSum
}

// 获取数组的最长累乘子串
fun multipleSum(array: IntArray): Int {
  var maxSum = array[0]
  var minSum = array[0]
  var result = array[0]
  for (v in 1 until array.size) {
    val curMax = maxSum * array[v]
    val curMin = minSum * array[v]
    maxSum = Math.max(Math.max(curMax, curMin), array[v])
    minSum = Math.min(Math.min(curMax, curMin), array[v])
    result = Math.max(Math.max(maxSum, minSum), result)
  }
  return result
}

fun main() {
  var array = intArrayOf(4, 5, 6, -10, -9, -3, 7, 8, 9)
  println(addSum(array))
  println(multipleSum(array))

  array = intArrayOf(4, -3)
  println(addSum(array))

  array = intArrayOf(-4, 3)
  println(addSum(array))
}
