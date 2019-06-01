package operator

import com.smore.rpncalculator.exception.InsucientParametersException
import com.smore.rpncalculator.model.NumberStack
import com.smore.rpncalculator.operator.AddOperator
import org.junit.Test

class AddOperatorTest {
    @Test fun addOperatorSuccess() {
        val numberStack = NumberStack()
            .apply { pushNumber("1") }
            .apply { pushNumber("2") }
        AddOperator().operate(numberStack)
        assert(numberStack.popNumber().number == "3".toBigDecimal())
    }

    @Test(expected = InsucientParametersException::class)
    fun addOperatorFailed() {
        val numberStack = NumberStack()
            .apply { pushNumber("1") }
        AddOperator().operate(numberStack)
    }
}