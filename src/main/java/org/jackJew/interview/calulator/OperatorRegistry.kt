package org.jackJew.interview.calulator

object OperatorRegistry {

  private val decimalRegex = Regex("^[+-]?(\\d+[.])?\\d+$")

  private val controlOperators = mapOf(
      Pair("undo", Undo()),
      Pair("clear", Clear())
  )

  private val decimalOperators = mapOf(
      Pair("+", Add()),
      Pair("-", Subtract()),
      Pair("*", Multiply()),
      Pair("/", Divide()),
      Pair("sqrt", Sqrt())
  )

  private val dummy = Dummy()

  /**
   * Get the operator according to symbol.
   * Return dummy if symbol is decimal, if not found, return null.
   */
  operator fun get(symbol: String) =
      controlOperators[symbol] ?: decimalOperators[symbol] ?:
      if (symbol.matches(decimalRegex)) dummy else null

}