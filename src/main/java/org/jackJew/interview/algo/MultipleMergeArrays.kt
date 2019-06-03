package org.jackJew.interview.algo

/**
  合并n个已排序的数组
 */

/**
 * 通过最小堆
 */
fun doMergeByMinHeap(arrays: List<IntArray>): IntArray {
  val minHeap = Array(arrays.size) { HeapElement(0, arrays[0][0])}
  // set up minHeap
  for (index in 1 until arrays.size) {
    var i = index
    while (i > 0) { // sift up
      val parent = (i-1) / 2
      if (minHeap[parent].value <= arrays[index][0]) {
        break
      }
      minHeap[i] = minHeap[parent]
      i = parent
    }
    minHeap[i] = HeapElement(index, arrays[index][0])
  }

  val totalSize = arrays.map { it.size }.reduce(Int::plus)
  val resultArray = IntArray(totalSize)
  val arrayPositions = IntArray(arrays.size) { 0 }
  var index = 0
  resultArray[0] = minHeap[0].value
  arrayPositions[minHeap[0].arrayIndex]++
  var heapSize = arrays.size
  while (index++ < totalSize - 1) {  // iterate over all
    val arrayIndex = minHeap[0].arrayIndex
    var next: Int
    if (arrayPositions[arrayIndex] >= arrays[arrayIndex].size) { // 该数组已结束
      // 整体右移, 首元素为min
      System.arraycopy(minHeap, 1, minHeap, 0, --heapSize)
      val temp = minHeap[0]
      if (temp.value > minHeap[1].value) {
        minHeap[0] = minHeap[1]
        minHeap[1] = temp
      }
      resultArray[index] = minHeap[0].value
      arrayPositions[minHeap[0].arrayIndex]++
    } else {
      next = arrays[arrayIndex][arrayPositions[arrayIndex]] // 取上次数组的下一个元素
      if (next == resultArray[index - 1]
          || (next <= minHeap[1].value && next <= minHeap[2].value)) {
        resultArray[index] = next
        arrayPositions[arrayIndex]++
      } else {
        var i = 0
        while (i < heapSize / 2) {  // sift down
          var left = minHeap[i * 2 + 1].value
          var heapIndex = i * 2 + 1
          if (i * 2 + 2 < heapSize) {
            val right = minHeap[i * 2 + 2].value
            if (left > right) { // choose least
              left = right; heapIndex = i * 2 + 2
            }
          }
          if (next <= left) break
          minHeap[i] = minHeap[heapIndex]
          i = heapIndex
        }
        minHeap[i] = HeapElement(arrayIndex, next)
        // 取小堆的首元素
        resultArray[index] = minHeap[0].value
        arrayPositions[minHeap[0].arrayIndex]++
      }
    }
  }
  // 将余下的小堆元素填充到数组尾部
  minHeap.copyOfRange(1, heapSize).map { it.value }.sorted()
      .forEach { resultArray[index++] = it }
  return resultArray
}

class HeapElement(val arrayIndex: Int, val value: Int)

/**
 * 两两合并, 递归
 */
fun doMergeByRecursive(arrays: List<IntArray>): IntArray {
  if (arrays.isEmpty())
    return intArrayOf()
  if (arrays.size == 1)
    return arrays[0]
  if (arrays.size == 2)
    return doMerge(arrays[0], arrays[1])

  val mid = arrays.size / 2
  val halfPartLeft = doMergeByRecursive(arrays.subList(0, mid))
  val halfPartRight = doMergeByRecursive(arrays.subList(mid, arrays.size))
  return doMerge(halfPartLeft, halfPartRight)
}

private fun doMerge(array1: IntArray, array2: IntArray): IntArray {
  val resultArray = IntArray(array1.size + array2.size)
  var index = 0
  var i = 0
  var j = 0
  while (i < array1.size && j < array2.size) {
    if (array1[i] <= array2[j])
      resultArray[index++] = array1[i++]
    else
      resultArray[index++] = array2[j++]
  }
  for (x in i until array1.size) resultArray[index++] = array1[x]
  for (x in j until array2.size) resultArray[index++] = array2[x]
  return resultArray
}

fun main() {
  val array1 = (300..800).shuffled().subList(0, 100).sorted().toIntArray()
  val array2 = (1..200).shuffled().subList(0, 100).sorted().toIntArray()
  val array3 = (10..9999).shuffled().subList(0, 100).sorted().toIntArray()
  val array4 = (50..2666).shuffled().subList(0, 100).sorted().toIntArray()

  println(doMergeByMinHeap(listOf(array1, array2, array3, array4)).joinToString(","))

  println(doMergeByRecursive(listOf(array1, array2, array3, array4)).joinToString(","))
}