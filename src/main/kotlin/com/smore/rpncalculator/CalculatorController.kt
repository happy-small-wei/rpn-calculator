package com.smore.rpncalculator

import com.smore.rpncalculator.command.*
import com.smore.rpncalculator.exception.*
import com.sun.org.apache.bcel.internal.generic.PUSH
import org.apache.commons.lang3.math.NumberUtils
import org.reflections.Reflections
import java.util.*
import kotlin.reflect.KClass

fun main() {
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

class CalculatorController {

    private val numberStack = NumberStack()
    private val commandMapping = getCommandMapping()

    fun processCommands(input: String): CalculatorResult {
        val startPositionToCommand = getStartPositionToCommand(input)
        var calculatorResult: CalculatorResult? = null
        for ((startPosition, command) in startPositionToCommand) {
            try {
                execute(command)
            } catch (e: CalculatorException) {
                calculatorResult = CalculatorResult(numberStack.getNumbers(), e, command, startPosition)
                break
            }
        }
        calculatorResult = calculatorResult ?: CalculatorResult(numberStack.getNumbers())
        return calculatorResult
    }

    @Throws(CalculatorException::class)
    private fun execute(command: String) {
        if (isNumeric(command)) {
            numberStack.pushNumber(command)
        } else {
            commandMapping.getOrDefault(command.toLowerCase(), DefaultCommand()).operate(numberStack)
        }
    }

    private fun getStartPositionToCommand(input: String): SortedMap<Int, String> {
        val result = TreeMap<Int, String>(Int::compareTo)
        var start = 0
        while (start < input.length) {
            if (!input[start].isWhitespace()) {
                var end = start
                while (end < input.length && !input[end].isWhitespace()) {
                    end++
                }
                result[start] = input.substring(start, end)
                start = end
            }
            start++
        }
        return result
    }

    private fun isNumeric(strNum: String): Boolean {
        return NumberUtils.isCreatable(strNum)
    }

    companion object {
        fun getCommandMapping(): Map<String, Command> {
            val packageName = getPackageName(Command::class)
            val commandClasses = getCommandClassesUnderPackage(packageName)
            return commandClasses.associateBy(
                keySelector = { clazz ->
                    clazz.getAnnotation(com.smore.rpncalculator.annotation.Command::class.java).commandName
                },
                valueTransform = { clazz -> clazz.newInstance() }
            )
        }

        private fun getCommandClassesUnderPackage(packageName: String): List<Class<out Command>> {
            return Reflections(packageName)
                .getSubTypesOf(Command::class.java)
                .filter { clazz -> clazz.isAnnotationPresent(com.smore.rpncalculator.annotation.Command::class.java) }
        }

        private fun getPackageName(clazz: KClass<*>): String {
            val qualifiedClassName = clazz.qualifiedName!!
            return qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf("."))
        }
    }
}