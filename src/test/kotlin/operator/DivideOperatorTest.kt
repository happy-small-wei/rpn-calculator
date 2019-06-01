package operator

import com.smore.rpncalculator.exception.DividedByZeroException
import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.*
import org.junit.Test
import java.math.BigDecimal

class DivideOperatorTest {
    @Test fun divideOperatorTest() {
        val numberStack = NumberStack()
            .apply { pushNumber("42") }
            .apply { pushNumber("4") }
        DivideOperator().operate(numberStack)
        assert(numberStack.popNumber().number == "10.5".toBigDecimal())
    }

    @Test(expected = DividedByZeroException::class)
    fun divideOperatorFailedTest() {
        val numberStack = NumberStack()
            .apply { pushNumber("42") }
            .apply { pushNumber("0") }
        DivideOperator().operate(numberStack)
        assert(numberStack.popNumber().number == BigDecimal.ZERO)
    }
}