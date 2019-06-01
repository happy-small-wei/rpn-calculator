package operator

import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.ClearOperator
import org.junit.Test

class ClearOperatorTest {
    @Test fun clearOperatorTest() {
        val numberStack = NumberStack().apply { pushNumber("1") }
        ClearOperator().operate(numberStack)
        assert(numberStack.getSize() == 0)
    }
}