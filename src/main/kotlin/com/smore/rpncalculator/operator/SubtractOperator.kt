package com.smore.rpncalculator.operator

import com.smore.rpncalculator.annotation.Operator
import com.smore.rpncalculator.model.NumberNode

@Operator("-")
class SubtractOperator : TwoOperatorOperator() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.subtract(secondNumber)
    }
}