import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertFailsWith

internal class PositionTest {

    lateinit var testPos: Position


    @BeforeEach
    internal fun setUp() {
        testPos = Position(Triple(0,0, 'N'), Pair (0, 11))
    }

    @Test
    fun turnRightTest() {
        val dirs = "ESWNESWN"

        dirs.forEach {
            testPos.turnRight()
            assertEquals(0, testPos.x)
            assertEquals(0, testPos.y)
            assertEquals(it, testPos.dir)
        }
    }

    @Test
    fun turnLeftTest() {
        val dirs = "WSENWSEN"

        dirs.forEach {
            testPos.turnLeft()
            assertEquals(0, testPos.x)
            assertEquals(0, testPos.y)
            assertEquals(it, testPos.dir)
        }
    }

    @Test
    fun goForwardTest() {
        val dirs = "WSEN"

        dirs.forEach {
            testPos.turnLeft()
            testPos.goForward()
            if (it == 'N') {
                assertEquals(0, testPos.x)
                assertEquals(1, testPos.y)
                assertEquals(it, testPos.dir)
            } else {
                assertEquals(0, testPos.x)
                assertEquals(0, testPos.y)
                assertEquals(it, testPos.dir)
            }
        }
    }

    @Test
    fun getCurrentPosTest() {
        assertEquals("0 0 N", testPos.getCurrentPos())
        testPos = Position(Triple(8,3, 'S'), Pair (9, 11))
        assertEquals("8 3 S", testPos.getCurrentPos())
    }

    @Test
    fun failInitWithExceptionsTest() {
        assertFailsWith(InvalidInstructionException::class) {
            Position(Triple(1,2, 'T'), Pair (5, 5))
        }
        assertFailsWith(InvalidInstructionException::class) {
            Position(Triple(6,2, 'N'), Pair (5, 5))
        }
        assertFailsWith(InvalidInstructionException::class) {
            Position(Triple(1,-2, 'N'), Pair (5, 5))
        }
        assertFailsWith(InvalidInstructionException::class) {
            Position(Triple(-1,2, 'N'), Pair (-1, 5))
        }
    }
}