import com.smore.rpncalculator.CalculatorController
import com.smore.rpncalculator.exception.*
import com.smore.rpncalculator.model.NumberNode
import org.junit.Test

class CalculatorControllerTest {
    @Test fun processNumbersTest() {
        val result = CalculatorController().processCommands("1 2 3 4")
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 2 3 4")
    }

    @Test fun processCalculateCommandTest() {
        val result = CalculatorController().processCommands("1 2 3 4 sqrt * +")
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 8")
    }

    @Test fun processUndoCommandTest() {
        val result = CalculatorController().processCommands("1 2 3 4 sqrt * + + undo")
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 8")
    }

    @Test fun processRedoCommandTest() {
        val result = CalculatorController().processCommands("1 2 3 4 sqrt * +  undo redo")
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 8")
    }

    @Test(expected = DividedByZeroException::class)
    fun processDivideFailedTest() {
        val result = CalculatorController().processCommands("1 0 /")
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 0")
        result.exception?.apply { throw this }
    }

    @Test(expected = NegativeForSqrtException::class)
    fun processSqrtFailedTest() {
        val result = CalculatorController().processCommands("-1 sqrt")
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "-1")
        result.exception?.apply { throw this }
    }
}