package operator

import com.smore.rpncalculator.exception.NegativeForSqrtException
import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.*
import org.junit.Test

class SqrtOperatorTest {
    @Test fun sqrtOperatorTest() {
        val numberStack = NumberStack()
            .apply { pushNumber("1") }
        SqrtOperator().operate(numberStack)
        assert(numberStack.popNumber().number == "1".toBigDecimal())
    }

    @Test(expected = NegativeForSqrtException::class)
    fun sqrtOperatorFailedTest() {
        val numberStack = NumberStack()
            .apply { pushNumber("-1") }
        SqrtOperator().operate(numberStack)
    }
}