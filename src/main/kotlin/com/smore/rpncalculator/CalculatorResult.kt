package com.smore.rpncalculator

import java.lang.Exception
import java.lang.StringBuilder

class CalculatorResult(val numbers: List<NumberNode>,
                       val exception: Exception?,
                       val command: String,
                       val commandPosition: Int) {

    constructor(numbers: List<NumberNode>) : this(numbers, exception = null, command = "", commandPosition = -1)

    fun getExceptionMessage(): String? {
        return exception?.message
    }
}