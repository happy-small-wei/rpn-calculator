package com.smore.rpncalculator.command

import com.smore.rpncalculator.*
import com.smore.rpncalculator.exception.InsucientParametersException
import javax.naming.InsufficientResourcesException

abstract class Command {
    open val numOfOperator: Int = 0
    open val shouldStoreAfterCommand = true

    @Throws(InsufficientResourcesException::class)
    fun operate(numberStack: NumberStack) {
        validateBeforeExecute(numberStack)
        execute(numberStack)
    }

    @Throws(InsucientParametersException::class)
    protected fun validateBeforeExecute(numberStack: NumberStack) {
        if (!numberStack.hasEnoughNumber(numOfOperator)) {
            throw InsucientParametersException()
        }
    }

    protected abstract fun execute(numberStack: NumberStack)
}