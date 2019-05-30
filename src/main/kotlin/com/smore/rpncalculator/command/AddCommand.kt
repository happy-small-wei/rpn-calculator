package com.smore.rpncalculator.command

import com.smore.rpncalculator.*

@com.smore.rpncalculator.annotation.Command("+")
class AddCommand : TwoOperatorCommand() {
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.add(secondNumber)
    }
}