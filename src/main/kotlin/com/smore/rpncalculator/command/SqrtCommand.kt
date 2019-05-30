package com.smore.rpncalculator.command

import com.smore.rpncalculator.*

@com.smore.rpncalculator.annotation.Command("sqrt")
class SqrtCommand : Command() {
    override fun execute(numberStack: NumberStack) {
        val number = numberStack.popNumber()
        val result = number.sqrt()
        result.position = numberStack.getSize()
        numberStack.pushNumber(result)
    }
}