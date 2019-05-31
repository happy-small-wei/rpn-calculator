package com.smore.rpncalculator.operator

import com.smore.rpncalculator.model.NumberNode
import com.smore.rpncalculator.annotation.Operator

@Operator("*")
class MultiplyOperator() : TwoOperatorOperator() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.multiply(secondNumber)
    }
}