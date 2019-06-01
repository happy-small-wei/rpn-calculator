package model

import com.smore.rpncalculator.model.Command
import org.junit.Test

class CommandTest {
    @Test fun isNumericTest() {
        assert(Command("033232.990000").isNumeric())
        assert(Command(".99").isNumeric())
        assert(!Command("..99").isNumeric())
    }
}