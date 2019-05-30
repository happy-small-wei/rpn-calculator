package com.smore.rpncalculator

import java.lang.StringBuilder

class CalculatorView {
    fun printResult(calculatorResult: CalculatorResult) {
        val view = viewCalculateResult(calculatorResult)
        println(view)
    }

    private fun viewCalculateResult(calculatorResult: CalculatorResult): String {
        val result = StringBuilder()
        if (calculatorResult.exception != null) {
            result.append("operator ${calculatorResult.command} ")
                .append("(position: ${calculatorResult.commandPosition}): ")
                .append("${calculatorResult.getExceptionMessage()}").appendln()
        }
        result.append("stack:")
        calculatorResult.numbers.reversed().forEach { number ->
            result.append(" ${number.toPlainString(DECIMAL_PLACES_FOR_VIEW)}")
        }
        return result.toString()
    }

    companion object {
        const val DECIMAL_PLACES_FOR_VIEW = 10
    }
}
