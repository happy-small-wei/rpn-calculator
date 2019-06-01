package operator

import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.*
import org.junit.Test

class SubtractOperatorTest {
    @Test
    fun multiplyOperatorTest() {
        val numberStack = NumberStack()
            .apply { pushNumber("1") }
            .apply { pushNumber("2") }
        SubtractOperator().operate(numberStack)
        assert(numberStack.popNumber().number == "-1".toBigDecimal())
    }
}