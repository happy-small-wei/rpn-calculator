package com.smore.rpncalculator.command

import com.smore.rpncalculator.NumberStack

@com.smore.rpncalculator.annotation.Command("undo")
class UndoCommand : Command() {
    override fun execute(numberStack: NumberStack) {
        numberStack.loadPrevious()
    }
}