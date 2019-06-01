package com.smore.rpncalculator

import com.smore.rpncalculator.model.*

class CalculatorController {
    private val calculator = CalculatorService()

    fun processCommands(input: String): CalculatorResult {
        val commands = getCommands(input)
        var calculatorResult = CalculatorResult()
        for (command in commands) {
            calculatorResult = calculator.submit(command)
            if (calculatorResult.exception != null) {
                break
            }
        }
        return calculatorResult
    }

    private fun getCommands(input: String): List<Command> {
        val result = mutableListOf<Command>()
        var start = 0
        while (start < input.length) {
            if (!input[start].isWhitespace()) {
                var end = start
                while (end < input.length && !input[end].isWhitespace()) {
                    end++
                }
                val commandName = input.substring(start, end)
                Command(commandName.toLowerCase(), start + 1)
                    .run(result::add)
                start = end
            }
            start++
        }
        return result.toList()
    }
}