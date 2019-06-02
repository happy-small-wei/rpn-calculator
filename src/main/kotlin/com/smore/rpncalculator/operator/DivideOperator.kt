package com.smore.rpncalculator.operator

import com.smore.rpncalculator.annotation.Operator
import com.smore.rpncalculator.exception.DividedByZeroException
import com.smore.rpncalculator.model.NumberNode

@Operator("/")
class DivideOperator : TwoOperatorOperator() {
    @Throws(DividedByZeroException::class)
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.divide(secondNumber)
    }
}