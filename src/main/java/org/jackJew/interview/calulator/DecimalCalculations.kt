package org.jackJew.interview.calulator

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * Decimal calculations.
 */
class DecimalCalculations {

  companion object {
    val mathContext = MathContext(15, RoundingMode.HALF_EVEN)
    private const val displayPrecision = 10
    val two = BigDecimal(2)

    /**
     * Store decimal by pre-defined precision.
     */
    fun toDecimal(a: String) = BigDecimal(a, mathContext)

    /**
     * Print decimal by pre-defined displayPrecision.
     */
    fun toString(a: BigDecimal) =
        a.setScale(displayPrecision, RoundingMode.HALF_EVEN).stripTrailingZeros().toPlainString()
  }

  /**
   * Add two numbers.
   */
  fun add(a: BigDecimal, b: BigDecimal) = a.add(b, mathContext)

  /**
   * Subtract two numbers.
   */
  fun subtract(a: BigDecimal, b: BigDecimal) = a.subtract(b, mathContext)

  /**
   * Multiply two numbers.
   */
  fun multiply(a: BigDecimal, b: BigDecimal) = a.multiply(b, mathContext)

  /**
   * Divide two numbers.
   */
  fun divide(a: BigDecimal, b: BigDecimal) = a.divide(b, mathContext)

  /**
   * Sqrt one number.
   */
  fun sqrt(a: BigDecimal): BigDecimal {
    var init = BigDecimal.ZERO
    var res = a.divide(two, mathContext)
    while (init != res) {
      init = res
      res = a.divide(init, mathContext)
      res = res.add(init, mathContext)
      res = res.divide(two, mathContext)
    }
    return res
  }
}