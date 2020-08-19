package org.jackJew.interview.algo

import kotlin.random.Random

// 计算两个有序数组的中位数， 目标时间复杂度为: log(max(m,n))
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

// 计算两个有序数组的 topK
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

// 对于无序数组的中值或topk问题, 应随机选取数组的某个值a, 两个数组分别进行一轮ASC快排, 获得两个index(m1,m2);
// 若 m1+m2 = k 则退出,
// 若 < k, 随机取比a大的值, 在两个数组[m1,),[m2,)部分 重复进行;
// 若 > k, 随机取比a小的值, 在两个数组(,m1],(,m2]部分 重复进行.
fun findTopKByQuickSort(array1: IntArray, array2: IntArray, k: Int): Int {
  if (k > array1.size + array2.size)
    return -1  // not exists

  var pivot = Math.max(array1[0], array2[0]) // 第一轮
  val r1 = array1.size - 1
  val r2 = array2.size - 1
  val exists = hashSetOf(pivot)  // 已分配的pivots, 防止死循环
  while (true) {
    val m1 = findPivotIndex(array1, 0, r1, pivot)
    val m2 = findPivotIndex(array2, 0, r2, pivot)

    val L1 = if (array1[m1] <= pivot) m1 + 1 else m1
    val L2 = if (array2[m2] <= pivot) m2 + 1 else m2

    if (L1 + L2 == k)
      return pivot
    else if (L1 + L2 > k) {  // 偏大
      while(!exists.add(pivot)) {
        pivot = array1[Random.nextInt(m1)]
        if (exists.add(pivot)) break
        else pivot = array2[Random.nextInt(m2)]
      }
    } else {  // 偏小
      while(!exists.add(pivot)) {
        pivot = array1[Random.nextInt(m1, array1.size)]
        if (exists.add(pivot)) break
        else pivot = array2[Random.nextInt(m2, array2.size)]
      }
    }
  }
}

// 对于巨量（20亿个）整型数据的topk问题（k较小，k=10000左右），首先siftUp建立k-size小顶堆，暂停，
// 分组100w一组，循环每个分组（每个分组内做快排，找topk，对前k对小顶堆做siftDown）。
// 最终k堆就是结果。

// 快排 index
fun findPivotIndex(array: IntArray, left: Int, right: Int, pivot: Int): Int {  // ASC
  if (left < right) {
    var i = if (left < 0) 0 else left
    var j = if (right >= array.size) array.size - 1 else right
    val pivot = array[i]
    while (i < j) {
      while ( i < j && array[j] > pivot) {
        j--
      }
      array[i] = array[j]
      while (i < j && array[i] <= pivot) {
        i++
      }
      array[j] = array[i]
    }
    array[i] = pivot
    return i
  } else
    return left
}

fun main() {
  // testing
  println(findMedian(intArrayOf(1,7,8), intArrayOf(2, 3,10,11,12,13,14,15,16, 17, 18, 22)))

  println(findTopK(intArrayOf(1, 3, 14,15,16), intArrayOf( 2,9,10,11,12,13), 4))

//  println(findPivotIndex(intArrayOf(30,40, 10,20), 0, 3, 1))  // 0
//  println(findPivotIndex(intArrayOf(30,40, 10,20), 0, 3, 11)) // 1
//  println(findPivotIndex(intArrayOf(30,40, 10,20), 0, 3, 31)) // 2
//  println(findPivotIndex(intArrayOf(30,40, 10,20), 0, 3, 100)) // 3

  println(findTopKByQuickSort(intArrayOf(14, 15, 16, 1, 3), intArrayOf( 11,12,13,2, 9,10), 4))
}
