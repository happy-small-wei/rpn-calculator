package operator

import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.*
import org.junit.Test

class UndoOperatorTest {
    @Test
    fun undoOperatorWithoutHistoryVersionTest() {
        val numberStack = NumberStack()
        UndoOperator().operate(numberStack)
        assert(numberStack.getSize() == 0)
    }

    @Test
    fun undoOperatorWithHistoryVersionTest() {
        val numberStack = NumberStack().apply { pushNumber("1") }.apply { pushNumber("2") }
        UndoOperator().operate(numberStack)
        assert(numberStack.popNumber().number == 1.toBigDecimal())
    }
}