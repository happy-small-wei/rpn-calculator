package com.smore.rpncalculator.command

import com.smore.rpncalculator.NumberNode
import com.smore.rpncalculator.annotation.Command
import com.smore.rpncalculator.exception.DividedByZeroException

@Command("/")
class DivideCommand : TwoOperatorCommand() {
    @Throws(DividedByZeroException::class)
    override fun calculateTwoOperator(firstNumber: NumberNode, secondNumber: NumberNode): NumberNode {
        return firstNumber.divide(secondNumber)
    }
}