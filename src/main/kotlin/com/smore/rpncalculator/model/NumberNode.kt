package com.smore.rpncalculator.model

import com.smore.rpncalculator.RpnCalculatorCommandTool
import com.smore.rpncalculator.exception.DividedByZeroException
import java.math.BigDecimal

class NumberNode(private val number: BigDecimal) {

    var position: Int = -1

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

    fun sqrt(): NumberNode {
        val result = BigDecimal.valueOf(Math.sqrt(number.toDouble()))
        return NumberNode(result.setScale(DECIMAL_PLACES_FOR_SAVING,
            ROUNDING_MODE))
    }

    fun toPlainString(decimalPlacesForView: Int): String {
        return number.setScale(decimalPlacesForView, ROUNDING_MODE).stripTrailingZeros().toPlainString()
    }

    companion object {
        private val DECIMAL_PLACES_FOR_SAVING =
            RpnCalculatorCommandTool.getProperty("decimalPlacesForSave", "15").toInt()
        private const val ROUNDING_MODE = BigDecimal.ROUND_DOWN
    }
}
