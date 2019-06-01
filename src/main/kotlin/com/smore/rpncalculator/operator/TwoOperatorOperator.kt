package com.smore.rpncalculator.operator

import com.smore.rpncalculator.exception.CalculatorException
import com.smore.rpncalculator.model.*

abstract class TwoOperatorOperator : Operator() {
    override val numOfOperator = 2

    abstract fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode

    @Throws(CalculatorException::class)
    override fun execute(numberStack: NumberStack) {
        val newerNumber = numberStack.popNumber()
        val olderNumber = numberStack.popNumber()
        val result = calculateTwoOperator(olderNumber, newerNumber)
        result.position = numberStack.getSize()
        numberStack.pushNumber(result)
    }
}