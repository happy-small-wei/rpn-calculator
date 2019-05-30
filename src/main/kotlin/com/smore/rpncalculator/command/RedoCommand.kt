package com.smore.rpncalculator.command

import com.smore.rpncalculator.NumberStack

@com.smore.rpncalculator.annotation.Command("redo")
class RedoCommand: Command() {
    override fun execute(numberStack: NumberStack) {
        numberStack.loadFuture()
    }
}