package com.smore.rpncalculator.command

import com.smore.rpncalculator.NumberNode
import com.smore.rpncalculator.annotation.Command

@Command("*")
class MultiplyCommand() : TwoOperatorCommand() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.multiply(secondNumber)
    }
}