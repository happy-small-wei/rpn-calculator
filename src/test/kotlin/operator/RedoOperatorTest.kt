package operator

import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.*
import org.junit.Test

class RedoOperatorTest {
    @Test fun redoOperatorWithoutFutureVersionTest() {
        val numberStack = NumberStack()
        RedoOperator().operate(numberStack)
        assert(numberStack.getSize() == 0)
    }

    @Test fun redoOperatorWithFutureVersionTest() {
        val numberStack = NumberStack().apply { pushNumber("1") }
        UndoOperator().operate(numberStack)
        RedoOperator().operate(numberStack)
        assert(numberStack.popNumber().number == 1.toBigDecimal())
    }
}