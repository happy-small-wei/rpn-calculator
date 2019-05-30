package com.smore.rpncalculator.command

import com.smore.rpncalculator.NumberStack

@com.smore.rpncalculator.annotation.Command("clear")
class ClearCommand: Command() {
    override fun execute(numberStack: NumberStack) {
        numberStack.clear()
    }
}