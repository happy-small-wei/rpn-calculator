package com.smore.rpncalculator

import com.smore.rpncalculator.model.CalculatorResult

class CalculatorViewer {
    fun printResult(calculatorResult: CalculatorResult) {
        val view = viewCalculateResult(calculatorResult)
        println(view)
    }

    private fun viewCalculateResult(calculatorResult: CalculatorResult): String {
        val result = StringBuilder()
        if (calculatorResult.exception != null) {
            result.append("operator ${calculatorResult.command} ")
                .append("(position: ${calculatorResult.commandPosition}): ")
                .append("${calculatorResult.getExceptionMessage()}")
                .append(":${calculatorResult.exception.cause?.message}").appendln()
        }
        result.append("stack:")
        calculatorResult.numbers.reversed().forEach { number ->
            result.append(" ${number.toPlainString(DECIMAL_PLACES_FOR_VIEW)}")
        }
        return result.toString()
    }

    companion object {
        val DECIMAL_PLACES_FOR_VIEW =
            RpnCalculatorCommandTool.getProperty("decimalPlacesForView", "10").toInt()
    }
}
