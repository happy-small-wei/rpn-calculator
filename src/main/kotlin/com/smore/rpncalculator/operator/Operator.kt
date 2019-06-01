package com.smore.rpncalculator.operator

import com.smore.rpncalculator.exception.*
import com.smore.rpncalculator.model.NumberStack
import java.lang.Exception
import javax.naming.InsufficientResourcesException

abstract class Operator {
    open val numOfOperator: Int = 0

    @Throws(CalculatorException::class)
    fun operate(numberStack: NumberStack) {
        validateBeforeExecute(numberStack)
        try {
            execute(numberStack)
        } catch (exception: CalculatorException) {
            numberStack.loadCurrent()
            throw exception
        } catch (exception: Exception) {
            numberStack.loadCurrent()
            throw CalculatorException("internal error").apply { initCause(exception) }
        }
    }

    @Throws(InsucientParametersException::class)
    protected fun validateBeforeExecute(numberStack: NumberStack) {
        if (!numberStack.hasEnoughNumber(numOfOperator)) {
            throw InsucientParametersException()
        }
    }

    protected abstract fun execute(numberStack: NumberStack)
}