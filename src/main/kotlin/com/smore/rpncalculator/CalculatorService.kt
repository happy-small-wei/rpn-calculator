package com.smore.rpncalculator

import com.smore.rpncalculator.exception.CalculatorException
import com.smore.rpncalculator.model.*
import com.smore.rpncalculator.operator.*
import org.reflections.Reflections
import kotlin.reflect.KClass

class CalculatorService {
    private val numberStack = NumberStack()

    @Throws(CalculatorException::class)
    fun submit(command: Command): CalculatorResult {
        return try {
            if (command.isNumeric()) {
                numberStack.pushNumber(command.getNumber())
            } else {
                operatorMapping.getOrDefault(command.name, DefaultOperator()).operate(numberStack)
            }
            CalculatorResult(numberStack.getNumbers())
        } catch (exception: CalculatorException) {
            CalculatorResult(numberStack.getNumbers(), exception, command)
        }
    }

    companion object {
        private val operatorMapping = scanOperatorMapping()

        private fun scanOperatorMapping(): Map<String, Operator> {
            val packageName = getPackageName(Operator::class)
            val commandClasses = getCommandClassesUnderPackage(packageName)
            return commandClasses.associateBy(
                keySelector = { clazz ->
                    clazz.getAnnotation(com.smore.rpncalculator.annotation.Operator::class.java).operatorName
                },
                valueTransform = { clazz -> clazz.newInstance() }
            )
        }

        private fun getCommandClassesUnderPackage(packageName: String): List<Class<out Operator>> {
            return Reflections(packageName)
                .getSubTypesOf(Operator::class.java)
                .filter { clazz -> clazz.isAnnotationPresent(com.smore.rpncalculator.annotation.Operator::class.java) }
        }

        private fun getPackageName(clazz: KClass<*>): String {
            val qualifiedClassName = clazz.qualifiedName!!
            return qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf("."))
        }
    }
}