package com.smore.rpncalculator.operator

import com.smore.rpncalculator.annotation.Operator
import com.smore.rpncalculator.model.NumberNode

@Operator("*")
class MultiplyOperator : TwoOperatorOperator() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.multiply(secondNumber)
    }
}