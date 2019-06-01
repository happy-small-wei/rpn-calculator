package com.smore.rpncalculator.operator

import com.smore.rpncalculator.model.NumberStack

@com.smore.rpncalculator.annotation.Operator("clear")
class ClearOperator: Operator() {
    override fun execute(numberStack: NumberStack) {
        if (numberStack.getSize() != 0) {
            numberStack.clear()
        }
    }
}