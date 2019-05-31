package com.smore.rpncalculator.model

import java.lang.Exception

class CalculatorResult(val numbers: List<NumberNode>,
                       val exception: Exception?,
                       val command: String,
                       val commandPosition: Int) {

    constructor(numbers: List<NumberNode>)
            : this(numbers, exception = null, command = "", commandPosition = -1)

    constructor(numbers: List<NumberNode>, exception: Exception, command: Command)
            : this(numbers, exception, command.name, command.position)

    constructor(): this(emptyList())

    fun getExceptionMessage(): String? {
        return exception?.message
    }
}