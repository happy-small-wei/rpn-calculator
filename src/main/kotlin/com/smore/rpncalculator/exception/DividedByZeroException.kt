package com.smore.rpncalculator.exception

class DividedByZeroException(message: String) : CalculatorException(message) {
    constructor() : this("divided by zero")
}