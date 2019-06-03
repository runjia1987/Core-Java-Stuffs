package org.jackJew.interview.algo

/**
 * 折叠二分查找
 */
val array = intArrayOf(100,110,120,1,2,30,40,50,60,70,80,90)
//(100, 108, 150, 388, 988, 1000, 6, 26, 38, 72, 80, 81, 82, 83, 84)
//

fun binarySearchBreak(array: IntArray, left: Int, right: Int, ele: Int): Int {
  if (left == right) {
    return left
  }
  var i = left
  var j = right
  while (i < j) {
    val mid = (i + j) ushr 1
    if (ele == array[mid])
      return mid
    else if (array[i] < array[mid]) {
      if (ele >= array[i] && ele < array[mid]) {
        j = mid - 1
      } else {
        i = mid + 1
      }
    } else {
      if (ele <= array[j] && ele > array[mid]) {
        i = mid + 1
      } else {
        j = mid - 1
      }
    }
  }
  if (i == j && array[i] == ele)
    return i
  return -1
}

fun main() {
  println(binarySearchBreak(array, 0, array.size - 1, 2))
  println(binarySearchBreak(array, 0, array.size - 1, 38))
}