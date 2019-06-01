package model

import com.smore.rpncalculator.*
import com.smore.rpncalculator.exception.*
import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.DivideOperator
import org.junit.Test
import java.math.BigDecimal
import kotlin.math.exp

class NumberStackTest {
    @Test fun pushAndPopTest() {
        val stack = NumberStack()
        stack.pushNumber("1")
        stack.pushNumber("2")
        assert(stack.popNumber().toPlainString(CalculatorView.DECIMAL_PLACES_FOR_VIEW) == "2")
        assert(stack.popNumber().toPlainString(CalculatorView.DECIMAL_PLACES_FOR_VIEW) == "1")
    }

    @Test fun pushToSizeLimitedStackTest() {
        val stack = NumberStack()
        for (i in 0..LIMIT_OF_STACK_SIZE) {
            stack.pushNumber(i.toString())
        }
        for (i in LIMIT_OF_STACK_SIZE..1) {
            assert(stack.popNumber().number == i.toBigDecimal())
        }
    }

    @Test(expected = PopFromEmptyStackException::class)
    fun popFailedTest() {
         NumberStack().popNumber()
    }

    @Test fun hasEnoughNumberTest() {
        val stack = NumberStack()
        stack.pushNumber("1")
        stack.pushNumber("2")
        assert(stack.hasEnoughNumber(1))
        assert(!stack.hasEnoughNumber(3))
    }

    @Test fun loadPreviousTest() {
        val firstNumber = "1"
        val stack = NumberStack()
        stack.pushNumber(firstNumber)
        stack.pushNumber("2")
        stack.loadPrevious()
        assert(stack.popNumber().number == firstNumber.toBigDecimal())
    }

    @Test fun loadCurrentTest() {
        val stack = NumberStack()
        stack.pushNumber("1")
        stack.pushNumber("0")
        try {
            DivideOperator().operate(stack)
        } catch (exception: DividedByZeroException) {
            stack.loadCurrent()
        }
        assert(stack.popNumber().number == BigDecimal.ZERO)
    }

    @Test fun loadFutureTest() {
        val secondNumber = "1"
        val stack = NumberStack()
        stack.pushNumber("1")
        stack.pushNumber(secondNumber)
        stack.loadPrevious()
        stack.loadFuture()
        assert(stack.popNumber().number == secondNumber.toBigDecimal())
    }

    @Test fun getNumbersTest() {
        val stack = NumberStack()
        for (i in 2 downTo 0) {
            stack.pushNumber(i.toString())
        }
        stack.getNumbers().withIndex().forEach { assert(it.index.toBigDecimal() == it.value.number) }
    }

    companion object {
        private val LIMIT_OF_STACK_SIZE =
            RpnCalculatorCommandTool.getProperty(propertyName = "limitOfStackSize", default = "20").toInt()
    }
}