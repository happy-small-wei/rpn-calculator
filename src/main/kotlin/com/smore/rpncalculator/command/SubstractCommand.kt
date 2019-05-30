package com.smore.rpncalculator.command

import com.smore.rpncalculator.NumberNode
import com.smore.rpncalculator.annotation.Command

@Command("-")
class SubstractCommand : TwoOperatorCommand() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.subtract(secondNumber)
    }
}