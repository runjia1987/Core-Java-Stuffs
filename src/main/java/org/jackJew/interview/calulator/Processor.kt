package org.jackJew.interview.calulator

import java.lang.StringBuilder
import java.math.BigDecimal
import java.util.Stack

/**
 * Input & output processor.
 */
object Config {
  const val SPLIT = " "
  const val WHITESPACE = ' '
}

class Processor {
  // 数值栈
  private val stack = Stack<BigDecimal>()

  /**
   * 解释执行.
   */
  fun interprete(input: String): String {
    val expr = input.trim()
    if (expr.isEmpty())
      return ""

    val length = expr.length
    var pos = 0
    var errorMessage: String? = null
    loop@ while (pos < length) {
      var end = expr.indexOf(Config.SPLIT, pos)
      if (end == -1) {
        end = length
      }
      val symbol = expr.substring(pos, end)
      when (val oper = OperatorRegistry[symbol]) {
        null -> errorMessage = "operator $symbol (position: $pos): not recognized"
        is Dummy -> {
          DecimalCalculations.toDecimal(symbol).run {
            oper.calc(arrayOf(this))
            stack.push(this)
          }
        }
        is ControlOperator -> oper.calc(stack)
        is DecimalOperator -> {
          val requiredArgs = oper.requiredArgs()
          val list = mutableListOf<BigDecimal>()
          // validate
          if (stack.size < requiredArgs) {
            errorMessage = "operator $symbol (position: $pos): insufficient parameters"
            break@loop
          }
          for (x in 0 until requiredArgs) {
            list.add(0, stack.pop())
          }
          try {
            stack.push(oper.calc(list.toTypedArray()))
          } catch (ex: Exception) {
            errorMessage = "exception: ${ex.message}"
          }
        }
      }
      pos += symbol.length
      if (errorMessage != null || end == length) break
      while (++pos < length && expr[pos] == Config.WHITESPACE); // skip whitespace
    }
    val output = StringBuilder()
    if (errorMessage != null) {
      output.append(errorMessage).append("\n")
    }
    output.append(stack.joinToString(Config.SPLIT) { DecimalCalculations.toString(it) })

    return output.toString()
  }
}