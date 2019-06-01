package com.smore.rpncalculator.exception

class PopFromEmptyStackException(message: String) : CalculatorException(message) {
    constructor() : this("attepmt pop from empty stack")
}