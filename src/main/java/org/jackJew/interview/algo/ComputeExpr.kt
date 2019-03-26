package org.jackJew.interview.algo

import org.junit.Assert
import java.util.Stack

/**
 * expression calculation.
 */
fun main() {

  Assert.assertEquals(compute("1+2*3-(5-6*7)+8"), 52)

  Assert.assertEquals(compute("1+2*3-(5-6/(7-2*2))+8"), 12)
}

fun compute(expr: String): Int {
  val numStack = Stack<Int>()
  val operStack = Stack<Char>()
  var index = 0
  while (index < expr.length) {
    val c = expr[index]
    if (c.toInt() in 48..57) {
      numStack.push("$c".toInt())
    } else if (c == '/' || c == '*') {
      val c2 = expr[index + 1]
      if (c2.toInt() in 48..57) {
        index++
        numStack.push(oper(numStack.pop(), "$c2".toInt(), c))
      } else {
        operStack.push(c)
      }
    } else if (c == ')') {
      val nums = Stack<Int>()
      val ops = Stack<Char>()
      while (operStack.isNotEmpty()) {
        nums.push(numStack.pop())
        val op = operStack.pop()
        ops.push(op)
        if (op == '(') { // find nearest embracing ( character
          break
        }
      }
      val temp = calc(nums, ops)
      if (operStack.peek() ==  '/' || operStack.peek() == '*') { // preceding calc
        numStack.push(oper(numStack.pop(), temp, operStack.pop()))
      } else {
        numStack.push(temp)
      }
    } else {
      operStack.push(c)
    }
    index++
  }
  numStack.reverse()
  operStack.reverse()
  return calc(numStack, operStack)
}

/**
 * Calculate + - for final result.
 */
fun calc(numStack: Stack<Int>, operStack: Stack<Char>): Int {
  var result = 0

  if (operStack.isNotEmpty()) {
    var op = operStack.pop()
    if (op == '(') {
      if (operStack.isEmpty()) {
        return numStack.pop().toInt()
      } else {
        op = operStack.pop()
      }
    }

    val a = numStack.pop()
    val b = numStack.pop()
    result = oper(a, b, op)
  }
  while (operStack.isNotEmpty()) {
    result = oper(result, numStack.pop(), operStack.pop())
  }
  return result
}

/**
 * Quick execute + - / *.
 */
fun oper(a: Int, b: Int, op: Char) =
    when (op) {
      '/' -> a / b
      '*' -> a * b
      '+' -> a + b
      '-' -> a - b
      else -> 0
    }