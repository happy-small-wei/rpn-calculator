package com.smore.rpncalculator.operator

import com.smore.rpncalculator.model.NumberStack

@com.smore.rpncalculator.annotation.Operator("sqrt")
class SqrtOperator : Operator() {
    override fun execute(numberStack: NumberStack) {
        val number = numberStack.popNumber()
        val result = number.sqrt()
        result.position = numberStack.getSize()
        numberStack.pushNumber(result)
    }
}