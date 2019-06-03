package org.jackJew.interview.algo

// 计算两个有序数组的中位数，或者topK问题. 目标时间复杂度为: log(max(m,n))

// 中位数
// 插入虚拟节点符号保证奇数size
fun findMedian(array1: IntArray, array2: IntArray): Double {
  val m = array1.size  // choose smaller array
  val n = array2.size
  if (m > n) {
    return findMedian(array2, array1)
  }
  var L1 = 0  // array1 leftMax
  var L2 = 0  // array2 leftMax
  var r1 = 0  // array1 rightMin
  var r2 = 0  // array2 rightMin
  var low = 0
  var high = 2 * m  // 虚插入虚拟节点符号
  while (low <= high) {
    val c1 = (low + high) / 2   // 二分的点
    val c2 = (m + n) - c1 // 虚插入虚拟节点符号
    L1 = if (c1 == 0) Integer.MIN_VALUE else array1[(c1 -1) / 2]
    r1 = if (c1 == 2 * m) Integer.MAX_VALUE else array1[c1 / 2]
    L2 = if (c2 == 0) Integer.MIN_VALUE else array2[(c2 - 1) / 2]
    r2 = array2[c2 / 2]

    if(L1 > r2) {
      high = c1 -1
    } else if (L2 > r1) {
      low = c1 + 1
    } else
      break
  }

  return (Math.max(L1, L2) + Math.min(r1, r2)).toDouble() / 2
}

// topK
fun findTopK(array1: IntArray, array2: IntArray, k: Int): Int {
  val m = array1.size
  val n = array2.size
  if (m > n) {
    return findTopK(array2, array1, k)
  }
  var L1 = 0
  var L2 = 0
  var r1 = 0
  var r2 = 0
  var low = 0
  var high = Math.min(k, m)
  while (low < high) {
    val c1 = (low + high) / 2
    val c2 = k - c1 - 2
    L1 = if (c1 < 0) Int.MIN_VALUE else array1[c1]
    r1 = if (c1 == m - 1) Int.MAX_VALUE else array1[c1 + 1]
    L2 = if (c2 < 0) Int.MIN_VALUE else array2[c2]
    r2 = array2[c2 + 1]

    if (L1 > r2) {
      high--
    } else if (L2 > r1) {
      low++
    } else
      break
  }
  return Math.max(L1, L2)
}

fun main() {
  // testing
  println(findMedian(intArrayOf(1,7,8), intArrayOf(2, 3,10,11,12,13,14,15,16, 17, 18, 22)))

  println(findTopK(intArrayOf(1, 3, 14,15,16), intArrayOf( 2,9,10,11,12,13), 4))
}