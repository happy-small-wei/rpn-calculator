import com.smore.rpncalculator.*
import com.smore.rpncalculator.exception.*
import com.smore.rpncalculator.model.*
import org.junit.Test

class CalculatorServiceTest {
    @Test
    fun submitNumbersTest() {
        val result = CalculatorService().submit(Command("1"))
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1")
    }

    @Test
    fun submitCalculateCommandTest() {
        val service = CalculatorService().apply {
            listOf("1", "2").forEach { commandName ->
                this.submit(Command(commandName))
            }
        }
        val result = service.submit(Command("+"))
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "3")
    }

    @Test
    fun submitUndoCommandTest() {
        val service = CalculatorService().apply {
            listOf("1", "2", "+").forEach { commandName ->
                this.submit(Command(commandName))
            }
        }
        val result = service.submit(Command("undo"))
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 2")
    }

    @Test
    fun submitRedoCommandTest() {
        val service = CalculatorService().apply {
        listOf("1", "2", "+", "undo").forEach { commandName ->
            this.submit(Command(commandName))
        }
    }
        val result = service.submit(Command("redo"))
        assert(result.exception == null)
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "3")
    }

    @Test(expected = DividedByZeroException::class)
    fun processDivideFailedTest() {
        val service = CalculatorService().apply {
            listOf("1", "0").forEach { commandName ->
                this.submit(Command(commandName))
            }
        }
        val result = service.submit(Command("/"))
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "1 0")
        result.exception?.apply { throw this }
    }

    @Test(expected = NegativeForSqrtException::class)
    fun processSqrtFailedTest() {
        val service = CalculatorService().apply {
            listOf("-1").forEach { commandName ->
                this.submit(Command(commandName))
            }
        }
        val result = service.submit(Command("sqrt"))
        val numbers = result.numbers.reversed().map {
            it.toPlainString(NumberNode.DECIMAL_PLACES_FOR_SAVING)
        }.reduceRight { s, acc -> "$s $acc" }
        assert(numbers == "-1")
        result.exception?.apply { throw this }
    }
}