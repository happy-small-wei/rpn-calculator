package com.smore.rpncalculator.operator

import com.smore.rpncalculator.model.NumberNode

@com.smore.rpncalculator.annotation.Operator("+")
class AddOperator : TwoOperatorOperator() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.add(secondNumber)
    }
}