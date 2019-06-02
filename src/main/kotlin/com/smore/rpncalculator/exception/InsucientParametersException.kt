package com.smore.rpncalculator.exception

class InsucientParametersException(message: String) : CalculatorException(message) {
    constructor() : this("insucient parameters")
}