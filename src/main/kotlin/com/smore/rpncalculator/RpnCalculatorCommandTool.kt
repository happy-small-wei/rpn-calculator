package com.smore.rpncalculator

import java.util.*

fun main() {
   RpnCalculatorCommandTool().start()
}

class RpnCalculatorCommandTool {
    fun start() {
        val scanner = Scanner(System.`in`)
        val calculator = CalculatorController()
        val calculatorView = CalculatorView()
        while (scanner.hasNextLine()) {
            val inputLine = scanner.nextLine()
            if (inputLine.isNotBlank()) {
                calculator.processCommands(inputLine)
                    .apply(calculatorView::printResult)
            }
        }
    }

    companion object {
        val calculatorProperties = getProperties("application.properties")

        fun getProperty(propertyName: String, default: String): String {
            return calculatorProperties.getProperty(propertyName, default)
        }

        private fun getProperties(fileName: String): Properties {
            val fileStream = this.javaClass.getResourceAsStream("/$fileName")
            return Properties().apply { load(fileStream) }
        }
    }
}