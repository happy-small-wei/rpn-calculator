package com.smore.rpncalculator.operator

import com.smore.rpncalculator.exception.InsucientParametersException
import com.smore.rpncalculator.model.NumberStack
import javax.naming.InsufficientResourcesException

abstract class Operator {
    open val numOfOperator: Int = 0

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