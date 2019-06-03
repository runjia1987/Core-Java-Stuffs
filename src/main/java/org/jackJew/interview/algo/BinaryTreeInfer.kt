package org.jackJew.interview.algo

import org.jackJew.algorithm.TreeTraversing.Node

/**
 * 二叉树推导
 */
val preOrders = preOrder().apply { println(this.joinToString(",")) }
val middleOrders = middleOrder().apply { println(this.joinToString(",")) }
val postOrders = postOrder().apply { println(this.joinToString(",")) }

/**
 * 推导树 from preOrders & middleOrders
 */
fun calcBinary1(preOrders: List<Int>, startPre: Int, endPre: Int,
                middleOrders: List<Int>, startMiddle: Int, endMiddle: Int): Node? {
  if (startPre > endPre || startMiddle > endMiddle) {
    return null
  }
  val root = Node(preOrders[startPre])
  for (index in startMiddle..endMiddle) {
    if (middleOrders[index] == preOrders[startPre]) {
      root.left = calcBinary1(preOrders, startPre + 1, startPre + index - startMiddle,
          middleOrders, startMiddle,index - 1)
      root.right = calcBinary1(preOrders, startPre + index - startMiddle + 1, endPre,
          middleOrders, index + 1, endMiddle)
    }
  }
  return root
}

/**
 * 推导树 from postOrders & middleOrders
 */
fun calcBinary2(postOrders: List<Int>, startPost: Int, endPost: Int,
                middleOrders: List<Int>, startMiddle: Int, endMiddle: Int): Node? {
  if (startPost > endPost || startMiddle > endMiddle)
    return null
  val root = Node(postOrders[endPost])
  for (index in startMiddle..endMiddle) {
    if (middleOrders[index] == postOrders[endPost]) {
      root.left = calcBinary2(postOrders, startPost, startPost + index - startMiddle - 1,
          middleOrders, startMiddle, index - 1)
      root.right = calcBinary2(postOrders, startPost + index - startMiddle, endPost - 1,
          middleOrders, index + 1, endMiddle)
    }
  }
  return root
}

fun main() {
  val root1 = calcBinary1(preOrders, 0, preOrders.size - 1,
      middleOrders, 0, middleOrders.size - 1)

  val root2 = calcBinary2(postOrders, 0, postOrders.size - 1,
      middleOrders, 0, middleOrders.size - 1)
}