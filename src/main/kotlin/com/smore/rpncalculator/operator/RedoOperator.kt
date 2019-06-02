package com.smore.rpncalculator.operator

import com.smore.rpncalculator.model.NumberStack

@com.smore.rpncalculator.annotation.Operator("redo")
class RedoOperator : Operator() {
    override fun execute(numberStack: NumberStack) {
        numberStack.loadFuture()
    }
}