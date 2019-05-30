package com.smore.rpncalculator.exception

import java.lang.Exception

class InsucientParametersException(message: String) : CalculatorException(message) {
    constructor() : this("insucient parameters")
}