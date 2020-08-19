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

fun preOrder(): List<Int> {  //深度遍历 = 先根遍历
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

fun middleOrder() {
  val stack = Stack<Node>()
  var curr = root

  while (curr != null || !stack.isEmpty()) {
    if (curr != null) {
      stack.push(curr)
      curr = curr.left
    } else {
      curr = stack.pop()
      print("${curr.value}, ")
      curr = curr.right
    }
  }
}

fun postOrder(): List<Int> {
  val stack = Stack<Node>()
  stack.push(root)

  val queue = LinkedList<Int>()
  while (!stack.isEmpty()) {
    val node = stack.pop()
    queue.offerFirst(node.value)  // 类似链表翻转

    node.left?.also {
      stack.push(it)
    }
    node.right?.also {
      stack.push(it)
    }
  }
  return queue
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
fun traverseToLeafs() {
  // BFS 模式
  val nodeQueue = LinkedList<Node>()  //节点链表
  val strQueue= LinkedList<String>()  //字符串列表
  nodeQueue.offer(root)
  strQueue.offer("${root.value}")
  while (!nodeQueue.isEmpty()) {
    val node = nodeQueue.poll()
    if (node.left != null || node.right != null) {
      val str = strQueue.poll()
      if (node.left != null) {
        nodeQueue.offer(node.left)
        strQueue.offer("$str -> ${node.left.value}")  // 字符串继续拼接
      }
      if (node.right != null) {
        nodeQueue.offer(node.right)
        strQueue.offer("$str -> ${node.right.value}")  // 字符串继续拼接
      }
    }
  }
  strQueue.forEach { str -> print("$str, ")}
}

// 返回两个节点的最长公共先导节点, based on fun traverseToLeafs()
fun findLcp(node1: Int, Node2: Int): List<Node> {
  return listOf()
}

// 平衡树判断
fun isBalanced(root: Node) = getDepth(root) != -1

fun getDepth(node: Node?): Int {
  if (node == null) return 0
  val leftDepth = getDepth(node.left)
  if (leftDepth == -1) return -1
  val rightDepth = getDepth(node.right)
  if (rightDepth == -1) return -1

  return if (Math.abs(leftDepth - rightDepth) > 1) -1 else Math.max(leftDepth, rightDepth) + 1
}

// 对称镜像树判断
fun isSymmetric(root: Node) = symmetric(root.left, root.right)

fun symmetric(left: Node?, right: Node?): Boolean {
  if (left == null && right == null) {
    return true
  }
  if (left == null || right == null) {
    return false
  }
  if (left.value == right.value) {
    return symmetric(left.left, right.right) && symmetric(left.right, right.left)
  }
  return false
}

// 层级遍历, arrayList 对称判断
fun isSymmetricNonRecursive(root: Node): Boolean {
  var list = ArrayList<Node?>()
  list.add(root)
  while (list.isNotEmpty()) {
    val size = list.size
    if (size == 0) return true
    val mid = (size - 1) / 2
    val innerList = ArrayList<Node?>(size * 2)
    for (i in 0..mid) {
      val left = list[i]
      val right = list[size - i - 1]
      if (left?.value != right?.value) return false

      if (left != null) {
        innerList.add(left.left)
        innerList.add(left.right)
      }
      if (left != right && right != null) {
        innerList.add(right.left)
        innerList.add(right.right)
      }
    }
    list = innerList
  }
  return true
}

// 完全二叉树判断, 层级遍历，每一层为2的n-1幂次
fun isCompleteTree(root: Node): Boolean = false

// 二叉搜索树判断, 中序遍历是否递增
fun isBST(root: Node): Boolean = false

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

  println("hasNode: " + hasNode(root, Node(677)))  // true
  println("hasNode: " + hasNode(root, Node(677)))  // false

  println("traverseToLeafs: " + traverseToLeafs())

  println("isBalanced: " + isBalanced(root))

  println("isSymmeric: " + isSymmetric(root))
}
