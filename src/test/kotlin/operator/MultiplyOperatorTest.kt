package operator

import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.*
import org.junit.Test

class MultiplyOperatorTest {
    @Test fun multiplyOperatorTest() {
        val numberStack = NumberStack()
            .apply { pushNumber("1") }
            .apply { pushNumber("2") }
        MultiplyOperator().operate(numberStack)
        assert(numberStack.popNumber().number == "2".toBigDecimal())
    }
}