package com.smore.rpncalculator.model

import com.smore.rpncalculator.exception.CalculatorException

class CalculatorResult(val numbers: List<NumberNode>,
                       val exception: CalculatorException?,
                       val command: String,
                       val commandPosition: Int) {

    constructor(numbers: List<NumberNode>)
            : this(numbers, exception = null, command = "", commandPosition = -1)

    constructor(numbers: List<NumberNode>, exception: CalculatorException, command: Command)
            : this(numbers, exception, command.name, command.position)

    constructor() : this(emptyList())

    fun getExceptionMessage(): String? {
        return exception?.message
    }
}