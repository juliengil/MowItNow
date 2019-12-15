import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class MowItNowTest {
    lateinit var mowit: MowItNow
    val fileContent = "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA"

    @BeforeEach
    internal fun setUp() {
        mowit = MowItNow(fileContent)
    }

    @Test
    fun failInitWithExceptionsTest() {
        assertFailsWith(InvalidInstructionException::class) {
            MowItNow("5 5 N\n1 2 N\nDDAGDA")
        }
        assertFailsWith(InvalidInstructionException::class) {
            MowItNow("5 \n1 2 N\nDDAGDA")
        }
        assertFailsWith(InvalidInstructionException::class) {
            MowItNow("5 V\n1 2 N\nDDAGDA")
        }
        assertFailsWith(InvalidInstructionException::class) {
            MowItNow("5 5\n1 2 N\n")
        }
    }

}