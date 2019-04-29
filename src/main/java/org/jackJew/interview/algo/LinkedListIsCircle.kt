package org.jackJew.interview.algo

/**
 * 检测单链表是否有环.
 */
fun checkCircle(head: Node): Node? {
  // 排除两元素相互指向的特殊情形
  if (head == head.next?.next) {
    println(true)
  }
  var slow: Node? = head
  var fast: Node? = head
  while (fast?.next != null) {
    slow = slow?.next
    fast = fast.next?.next
    if (slow == fast) {  // 相交位置
      println(true)
      break
    }
  }
  // 取入口位置
  slow = head
  while (true) {
    fast = fast?.next
    slow = slow?.next
    if (slow == fast) {
      println("入口位置: ${slow?.value}")
      break
    }
  }
  return null
}

/**
 * 检测两个链表(非环)是否相交, 相交位置.
 * <br>
 * 如果链表有环，则调用checkCircle获得入口位置，遍历第二个环，必然到达相交位置
 */
fun checkListsIntersect(head1: Node, head2: Node) {
  var length1 = 0
  var length2 = 0
  var node1 = head1
  var node2 = head2
  while (node1.next != null || node2.next != null) {
    if (node1.next != null) {
      node1 = node1.next!!
      length1++
    }
    if (node2.next != null) {
      node2 = node2.next!!
      length2++
    }
  }
  if (node1 == node2) {  // 尾节点相同, 是相交
    println("checkListsIntersect is true")
  }

  node1 = head1
  node2 = head2
  val margin = length1 - length2 // 计算长度差距margin
  repeat(Math.abs(margin)) {
    if (margin > 0)  // 让较长的链表步进marigin位
      node1 = node1.next!!
    else node2 = node2.next!!
  }
  // 齐头并进遍历, 第一个相同元素即为相交位置
  while (true) {
    node1 = node1.next!!
    node2 = node2.next!!
    if (node1 == node2) {
      println("found intersect node: ${node1.value}")
      break
    }
  }
}

class Node(var next: Node?,
           val value: Int)

fun createLinkedList(array: IntArray): Node {
  var head = Node(null, array[0])  // no use.
  var current = Node(null, array[array.size - 1])  // no use.
  var previous: Node? = null
  for (value in array) {
    current = Node(null, value)
    if (previous != null) {
      previous.next = current
    } else {
      head = current
    }
    previous = current
  }
  // 建立一个环，尾部指向head
  current.next = head.next?.next

  return head
}

fun main() {
  val head = createLinkedList(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
  checkCircle(head)
}