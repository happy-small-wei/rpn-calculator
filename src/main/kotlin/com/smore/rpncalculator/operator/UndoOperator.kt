package com.smore.rpncalculator.operator

import com.smore.rpncalculator.model.NumberStack

@com.smore.rpncalculator.annotation.Operator("undo")
class UndoOperator : Operator() {
    override fun execute(numberStack: NumberStack) {
        numberStack.loadPrevious()
    }
}