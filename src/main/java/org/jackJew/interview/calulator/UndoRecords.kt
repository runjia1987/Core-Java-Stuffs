package org.jackJew.interview.calulator

import java.math.BigDecimal
import java.util.Stack

object UndoRecords {

  private const val PUT = "PUT"
  private const val TAKE = "TAKE"
  private const val CLEAR = "CLEAR"

  private val history = Stack<String>()

  /**
   * Record decimal producing.
   */
  fun recordProducing(decimal: BigDecimal) {
    history.push("$PUT $decimal")
  }

  /**
   * Record numbers consuming.
   */
  fun recordConsuming(args: Array<BigDecimal>) {
    if (args.isNotEmpty()) {
      history.push("$TAKE ${args.joinToString(Config.SPLIT) }")
    }
  }

  /**
   * Record numbers clear.
   */
  fun recordClear(args: Array<BigDecimal>) {
    if (args.isNotEmpty()) {
      history.push("$CLEAR ${args.joinToString(Config.SPLIT) }")
    }
  }

  /**
   * Deal undo.
   */
  fun deal(stack: Stack<BigDecimal>) {
    if (history.isEmpty())
      return
    val record = history.pop()
    when {
      record.startsWith(PUT) -> stack.pop()
      record.startsWith(CLEAR) ->
        record.replace("$CLEAR ", "").split(Config.SPLIT).forEach {
          stack.push(BigDecimal(it))
        }
      record.startsWith(TAKE) -> {
        stack.pop()
        record.replace("$TAKE ", "").split(Config.SPLIT).forEach {
          stack.push(BigDecimal(it))
        }
      }
    }
  }
}