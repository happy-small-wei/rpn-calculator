package com.smore.rpncalculator.exception

class NegativeForSqrtException(message: String) : CalculatorException(message) {
    constructor() : this("negative for sqrt")
}