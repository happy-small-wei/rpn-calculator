package com.smore.rpncalculator.model

import org.apache.commons.lang3.math.NumberUtils

class Command(val name: String, val position: Int) {
    fun isNumeric(): Boolean{
        return NumberUtils.isCreatable(name)
    }

    fun getNumber(): String {
        return name
    }
}