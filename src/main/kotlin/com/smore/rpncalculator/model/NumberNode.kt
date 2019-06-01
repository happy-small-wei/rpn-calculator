package com.smore.rpncalculator.model

import com.smore.rpncalculator.RpnCalculatorCommandTool
import com.smore.rpncalculator.exception.*
import java.math.BigDecimal

class NumberNode(number: BigDecimal) {

    val number: BigDecimal
    var position: Int = -1

    init {
        this.number = number.setScale(DECIMAL_PLACES_FOR_SAVING, ROUNDING_MODE).stripTrailingZeros()
    }

    constructor(number: String) : this(BigDecimal(number))

    fun add(otherNumber: NumberNode): NumberNode {
        val result = number.add(otherNumber.number)
        return NumberNode(result)
    }

    fun subtract(otherNumber: NumberNode): NumberNode {
        val result = number.subtract(otherNumber.number)
        return NumberNode(result)
    }

    fun multiply(otherNumber: NumberNode): NumberNode {
        val result = number.multiply(otherNumber.number)
        return NumberNode(result)
    }

    fun divide(otherNumber: NumberNode): NumberNode {
        if (otherNumber.number == BigDecimal.ZERO) {
            throw DividedByZeroException()
        }
        val result = number.divide(otherNumber.number,
            DECIMAL_PLACES_FOR_SAVING,
            ROUNDING_MODE)
        return NumberNode(result)
    }

    @Throws(NegativeForSqrtException::class)
    fun sqrt(): NumberNode {
        if (number < BigDecimal.ZERO) {
            throw NegativeForSqrtException()
        }
        val result = BigDecimal.valueOf(Math.sqrt(number.toDouble()))
        return NumberNode(result)
    }

    fun toPlainString(decimalPlacesForView: Int): String {
        return number.setScale(decimalPlacesForView, ROUNDING_MODE).stripTrailingZeros().toPlainString()
    }

    companion object {
        val DECIMAL_PLACES_FOR_SAVING =
            RpnCalculatorCommandTool.getProperty("decimalPlacesForSave", "15").toInt()
        const val ROUNDING_MODE = BigDecimal.ROUND_DOWN
    }
}
