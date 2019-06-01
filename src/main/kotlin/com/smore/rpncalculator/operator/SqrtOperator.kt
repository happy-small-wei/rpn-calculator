package com.smore.rpncalculator.operator

import com.smore.rpncalculator.exception.*
import com.smore.rpncalculator.model.NumberStack

@com.smore.rpncalculator.annotation.Operator("sqrt")
class SqrtOperator : Operator() {
    @Throws(NegativeForSqrtException::class)
    override fun execute(numberStack: NumberStack) {
        val number = numberStack.popNumber()
        val result = number.sqrt()
        result.position = numberStack.getSize()
        numberStack.pushNumber(result)
    }
}