package org.jackJew.interview.algo

import kotlin.random.Random

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

// 对于非有序数组的中值或topk问题, 应随机选取数组的某个值a, 两个数组分别进行一轮ASC快排, 获得两个index(m1,m2);
// 若 m1+m2 = k 则退出,
// 若 < k, 随机取比a大的值, 在两个数组[m1,),[m2,)部分 重复进行;
// 若 > k, 随机取比a小的值, 在两个数组(,m1],(,m2]部分 重复进行.
fun findTopKByQuickSort(array1: IntArray, array2: IntArray, k: Int): Int {
  if (k > array1.size + array2.size)
    return -1  // not exists

  var pivot = Math.max(array1[0], array2[0]) // 第一轮
  var L1 = 0
  var r1 = array1.size - 1
  var L2 = 0
  var r2 = array2.size - 1
  while (true) {
    val m1 = findPivotIndex(array1, L1, r1, pivot)
    val m2 = findPivotIndex(array2, L2, r2, pivot)
    val sum = m1 + m2
    if (sum == k - 1) return pivot
    else if (sum > k) {  // 偏大
      pivot = if (m1 > 0 && m1 < array1.size - 1) array1[m1 - 1] else array2[m2 - 1]
      if (m1 > 0 && m1 < array1.size - 1) {
        r1 = m1
      } else {
        r2 = m2
      }
    } else {  // 偏小
      pivot = if (m1 > 0 && m1 < array1.size - 1) array1[m1 + 1] else array2[m2 + 1]
      if (m1 > 0 && m1 < array1.size - 1) {
        L1 = m1
      } else {
        L2 = m2
      }
    }
  }
}

fun findPivotIndex(array: IntArray, left: Int, right: Int, pivot: Int): Int {  // ASC
  if (left < right) {
    var i = left
    var j = right
    val temp = array[i]
    while (i < j) {
      while ( i < j && array[j] > pivot)
        j--
      array[i] = array[j]
      while (i < j && array[i] <= pivot)
        i++
      array[j] = array[i]
    }
    array[i] = temp
    return i
  } else
    return left
}

fun main() {
  // testing
  println(findMedian(intArrayOf(1,7,8), intArrayOf(2, 3,10,11,12,13,14,15,16, 17, 18, 22)))

  println(findTopK(intArrayOf(1, 3, 14,15,16), intArrayOf( 2,9,10,11,12,13), 2))

  println(findTopKByQuickSort(intArrayOf(14, 15, 16, 1, 3), intArrayOf( 11,12,13,2, 9,10), 2))

  println(findPivotIndex(intArrayOf(10,20,30,40), 0, 0, 1))   // left-most
  println(findPivotIndex(intArrayOf(10,20,30,40), 0, 3, 100)) // right-most
}