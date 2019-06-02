package com.smore.rpncalculator.model

import org.apache.commons.lang3.math.NumberUtils

class Command(val name: String, val position: Int) {

    constructor(name: String) : this(name, -1)

    fun isNumeric(): Boolean {
        return NumberUtils.isCreatable(name)
    }

    fun getNumber(): String {
        return name
    }
}