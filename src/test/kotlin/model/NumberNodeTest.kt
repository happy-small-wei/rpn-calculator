package model

import com.smore.rpncalculator.exception.*
import com.smore.rpncalculator.model.NumberNode
import org.junit.Test
import java.lang.StringBuilder
import java.math.BigDecimal

class NumberNodeTest {
    @Test fun addTest() {
        val (num1, num2) = listOf("0.123456789", "123456789.0").map (::BigDecimal).map (::format)
        val result = NumberNode(num1).add(NumberNode(num2)).number
        val expected = num1.add(num2).run(::format)
        assert(result == expected)
    }

    @Test fun subtractTest() {
        val (num1, num2) = listOf("0.123456789", "123456789.0").map (::BigDecimal).map (::format)
        val result = NumberNode(num1).subtract(NumberNode(num2)).number
        val expected = num1.subtract(num2).run(::format)
        assert(result == expected)
    }

    @Test fun multiplyTest() {
        val (num1, num2) = listOf("0.123456789", "123456789.0").map (::BigDecimal).map (::format)
        val result = NumberNode(num1).multiply(NumberNode(num2)).number
        val expected = num1.multiply(num2).run(::format)
        assert(result == expected)
    }

    @Test fun divideTest() {
        val (num1, num2) = listOf("0.123456789", "123456789.0").map (::BigDecimal).map (::format)
        var result = NumberNode(num1).divide(NumberNode(num2)).number
        var expected = num1.divide(num2).run(::format)
        assert(result == expected)
        result = NumberNode(num2).divide(NumberNode(num1)).number
        expected = num2.divide(num1).run(::format)
        assert(result == expected)
    }

    @Test(expected = DividedByZeroException::class)
    fun divideFailedTest() {
        val num1 = BigDecimal("0.123456789").run(::format)
        val num2 = BigDecimal.ZERO
        NumberNode(num1).divide(NumberNode(num2))
    }

    @Test fun sqrtTest() {
        listOf("0.123456789", "123456789.0").map (::BigDecimal).forEach {num ->
            val result = NumberNode(num).sqrt().number
            val expected = BigDecimal.valueOf(Math.sqrt(num.toDouble())).run(::format)
            assert(result == expected)
        }
    }

    @Test(expected = NegativeForSqrtException::class)
    fun sqrtFailedTest() {
        val num = BigDecimal("-1").run(::format)
        NumberNode(num).sqrt()
    }

    @Test fun toPlainStringTest() {
        val expected = StringBuilder("0.")
        val num = StringBuilder("0.")
        for (i in 1..NumberNode.DECIMAL_PLACES_FOR_SAVING) {
            if (i < NumberNode.DECIMAL_PLACES_FOR_SAVING / 2) {
                expected.append('1')
                num.append('1')
            } else {
                num.append('0')
            }
        }
        assert(NumberNode(num.toString()).toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING) == expected.toString())
    }

    private fun format(number: BigDecimal): BigDecimal {
        return number.setScale(NumberNode.DECIMAL_PLACES_FOR_SAVING, NumberNode.ROUNDING_MODE).stripTrailingZeros()
    }
}