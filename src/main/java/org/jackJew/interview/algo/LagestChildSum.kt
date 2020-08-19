package org.jackJew.interview.algo

//获取数组的最长累加子串
fun addSum(array: IntArray): Int {
  var result = array[0]
  var curSum = array[0]
  for (v in 1 until array.size) {
    curSum = Math.max(curSum + array[v], array[v])
    result = Math.max(curSum, result)
  }
  return result
}

// 获取数组的最长累乘子串
fun multipleSum(array: IntArray): Int {
  var curMax = array[0]
  var curMin = array[0]
  var result = array[0]
  for (v in 1 until array.size) {
    val tempMax = curMax * array[v]
    val tempMin = curMin * array[v]
    curMax = Math.max(Math.max(tempMax, tempMin), array[v])
    curMin = Math.min(Math.min(tempMax, tempMin), array[v])
    result = Math.max(Math.max(curMax, curMin), result)
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
