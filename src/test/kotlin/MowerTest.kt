import org.junit.jupiter.api.Test
import kotlin.test.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class MowerTest {

    lateinit var testPos: Position

    @BeforeEach
    internal fun setUp() {
        testPos = Position(Triple(1,2, 'N'), Pair (5, 5))
    }

    @Test
    fun proceedInstructions() {
        val instructions = "GAGAGAGAA"
        val mow = Mower(testPos, instructions)
        mow.proceedInstructions()
        assertEquals(testPos.getCurrentPos(), "1 3 N")
    }

    @Test
    fun failInitWithExceptionTest() {
        val instructions = "GDAAATGGDDA"
        assertFailsWith(InvalidInstructionException::class) {
            Mower(testPos, instructions)
        }
    }
}