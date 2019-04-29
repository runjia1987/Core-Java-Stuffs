package org.jackJew.interview.algo

import org.jackJew.algorithm.TreeTraversing
import org.jackJew.algorithm.TreeTraversing.Node
import java.util.*

val root = TreeTraversing().run {
  setArray(intArrayOf(100, 90, 43, 456, 34, 0, 46, 9, 677, 1001, 78, 321, 2))
  setUpTree()
  root
}

fun preOrder(): List<Int> {
  val stack = Stack<Node>()
  stack.push(root)
  val list = mutableListOf<Int>()
  while (!stack.isEmpty()) {
    val node = stack.pop()
    list.add(node.value)
    node.right?.also { stack.push(it) }
    node.left?.also { stack.push(it) }
  }

  return list
}

fun middleOrder(): List<Int> {
  val stack = Stack<Node>()
  var curr = root

  val list = mutableListOf<Int>()
  while (curr != null || !stack.isEmpty()) {
    if (curr != null) {
      stack.push(curr)
      curr = curr.left
    } else {
      curr = stack.pop()
      list.add(curr.value)
      curr = curr.right
    }
  }

  return list
}

fun postOrder(): List<Int> {
  val stack = Stack<Node>()
  stack.push(root)

  val list = LinkedList<Int>()
  while (!stack.isEmpty()) {
    val node = stack.pop()
    node.left?.also {
      stack.push(it)
    }
    node.right?.also {
      stack.push(it)
    }
    list.offerFirst(node.value)  // 类似链表翻转
  }

  return list
}

fun main() {
  println("\npreOrder...")
  println(preOrder())

  println("\nmiddleOrder...")
  println(middleOrder())

  println("\npostOrder...")
  println(postOrder())
}