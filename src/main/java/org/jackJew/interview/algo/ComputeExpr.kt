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
    if (c == '/' || c == '*') {
      val c2 = getDigit(expr, index + 1)
      if (c2 != null) {
        numStack.push(oper(numStack.pop(), c2, c))
        index += c2.toString().length + 1
        continue
      } else {
        operStack.push(c)
      }
    } else if (c == ')') {
      val nums = Stack<Int>()
      val ops = Stack<Char>()
      while (operStack.isNotEmpty()) {
        nums.push(numStack.pop())
        val op = operStack.pop()
        if (op == '(') { // find nearest embracing ( character
          break
        }
        ops.push(op)
      }
      val temp = calc(nums, ops)
      if (operStack.peek() ==  '/' || operStack.peek() == '*') { // preceding calc
        numStack.push(oper(numStack.pop(), temp, operStack.pop()))
      } else {
        numStack.push(temp)
      }
    } else if (c == '+' || c == '-') {
      operStack.push(c)
    } else {
      val number = getDigit(expr, index)
      if (number != null) {
        numStack.push(number)
        index += number.toString().length
        continue
      } else
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
  while (operStack.isNotEmpty()) {
    val op = operStack.pop()
    val num1 = numStack.pop()
    val num2 = numStack.pop()

    numStack.push(oper(num1, num2, op))
  }
  return numStack.pop()
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

fun getDigit(input: String, i: Int): Int? {
  var j = i
  while (j < input.length) {
    val ch = input[j]
    if (ch in '0' .. '9') j++
    else break
  }
  return if(j > i) Integer.valueOf(input.substring(i, j)) else null
}