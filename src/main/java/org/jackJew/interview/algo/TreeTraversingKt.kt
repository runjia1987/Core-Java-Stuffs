package org.jackJew.interview.algo

import org.jackJew.algorithm.TreeTraversing
import org.jackJew.algorithm.TreeTraversing.Node
import java.util.Stack
import java.util.LinkedList

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

fun getMinDepth(head: Node?): Int {
  if (head == null)
    return 0
  if (head.left == null)
    return getMinDepth(head.right) + 1
  if (head.right == null)
    return getMinDepth(head.left) + 1
  return Math.min(getMinDepth(head.left), getMinDepth(head.right)) + 1
}

fun getMaxDepth(head: Node?): Int {
  if (head == null)
    return 0
  val left = getMaxDepth(head.left)
  val right = getMaxDepth(head.right)
  return Math.max(left, right) + 1
}

// 判断节点是否存在
fun hasNode(root: Node, node: Node): Boolean {
  if (node.value == root.value) {
    return true
  }
  var hasFound = false
  if (root.left != null)
    hasFound = hasNode(root.left, node)

  if (!hasFound && root.right != null)
    hasFound = hasNode(root.right, node)

  return hasFound
}

// 打印根节点至全部叶子节点的路径
fun traverseToLeafs(): List<String> {
  // BFS 模式
  val results = LinkedList<String>()
  val nodeQueue = LinkedList<Node>()  //节点链表
  val strQueue= LinkedList<String>()  //字符串列表
  nodeQueue.offer(root)
  strQueue.offer("")
  while (!nodeQueue.isEmpty()) {
    val node = nodeQueue.poll()
    val str = strQueue.poll()
    if (node.left == null && node.right == null) { //走到叶子节点
      results.add("$str -> ${node.value}") // 打印拼接的字符串
    }
    if (node.left != null) {
      nodeQueue.offer(node.left)
      strQueue.offer("$str -> ${node.value}")  // 字符串继续拼接
    }
    if (node.right != null) {
      nodeQueue.offer(node.right)
      strQueue.offer("$str -> ${node.value}")  // 字符串继续拼接
    }
  }
  return results
}

// 返回两个节点的最长公共先导节点, based on fun traverseToLeafs()
fun findLcp(node1: Int, Node2: Int): List<Node> {
  return listOf()
}

fun main() {
  println("\npreOrder...")
  println(preOrder())

  println("\nmiddleOrder...")
  println(middleOrder())

  println("\npostOrder...")
  println(postOrder())

  println("minDepth: ${getMinDepth(root)}")
  println("maxDepth: ${getMaxDepth(root)}")

  val node = Node(100)
  node.right = Node(9)
  println("minDepth: ${getMinDepth(node)}")

  println(hasNode(root, Node(677)))  // true
  println(hasNode(root, Node(677)))  // false

  println(traverseToLeafs())
}