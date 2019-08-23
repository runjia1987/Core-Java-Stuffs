package org.jackJew.interview.calulator

import java.math.BigDecimal
import java.util.Stack

/**
 * 操作符.
 */
interface Operator {
  /**
   * 需要的参数count.
   */
  fun requiredArgs(): Int

  /**
   * Recording.
   */
  fun record(args: Array<BigDecimal>)
}

/**
 * Operator([undo] [clear]) for decimals. 为控制操作符.
 */
abstract class ControlOperator: Operator {

  abstract fun calc(stack: Stack<BigDecimal>)

  override fun requiredArgs() = 0
}

/**
 * Operator([undo]).
 */
class Undo: ControlOperator() {

  override fun calc(stack: Stack<BigDecimal>) {
    UndoRecords.deal(stack)
  }

  // skip
  override fun record(args: Array<BigDecimal>) { }
}

/**
 * Operator([clear]).
 */
class Clear: ControlOperator() {

  override fun calc(stack: Stack<BigDecimal>) {
    record(stack.toTypedArray())
    stack.clear()
  }

  override fun record(args: Array<BigDecimal>) {
    UndoRecords.recordClear(args)
  }
}

/**
 * Operator([+] [-] [*] [/]) for decimals. 为计算操作符.
 */
abstract class DecimalOperator: Operator {

  companion object {
    val decimalCalc = DecimalCalculations()
  }

  fun calc(args: Array<BigDecimal>): BigDecimal {
    assert(args.size == requiredArgs())
    record(args)
    return compute(args)
  }

  abstract fun compute(args: Array<BigDecimal>): BigDecimal

  override fun record(args: Array<BigDecimal>) {
    UndoRecords.recordConsuming(args)
  }

  override fun requiredArgs() = 2
}

class Add: DecimalOperator() {
  override fun compute(args: Array<BigDecimal>) = decimalCalc.add(args[0], args[1])
}

class Subtract: DecimalOperator() {
  override fun compute(args: Array<BigDecimal>) = decimalCalc.subtract(args[0], args[1])
}

class Multiply: DecimalOperator() {
  override fun compute(args: Array<BigDecimal>) = decimalCalc.multiply(args[0], args[1])
}

class Divide: DecimalOperator() {
  override fun compute(args: Array<BigDecimal>) = decimalCalc.divide(args[0], args[1])
}

class Sqrt: DecimalOperator() {
  override fun compute(args: Array<BigDecimal>) = decimalCalc.sqrt(args[0])

  override fun requiredArgs() = 1
}

/**
 * Dummy operator.
 */
class Dummy: DecimalOperator() {
  override fun compute(args: Array<BigDecimal>) = args[0]  // just return

  override fun record(args: Array<BigDecimal>) {
    UndoRecords.recordProducing(args[0])
  }

  override fun requiredArgs() = 1
}