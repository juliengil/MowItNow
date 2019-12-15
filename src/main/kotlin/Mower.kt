import kotlin.system.exitProcess

/**
 * Represents a mower, which knows its position at all time, and the instructions it has to proceed
 * @param position Position object containing the position and the boundaries of the lawn
 * @param instructions String containing the instructions to follow
 */
class Mower(position: Position, instructions: String) {

    private var position = position
    private val instructions = instructions

    /**
     * Initializer of the object, checks if the instructions are in the right format
     * and throws an Exception otherwise
     */
    init {
        if (instructions.contains(Regex("[^AGD]"))) {
            throw InvalidInstructionException("The mower's instructions \"$instructions\" can only contain D, G, or A")
        }
    }

    /**
     * Execute the instructions one after the other, Char by Char
     */
    fun proceedInstructions() {
        instructions.forEach {
            when {
                it.equals('A', true) -> position.goForward()
                it.equals('G', true) -> position.turnLeft()
                it.equals('D', true) -> position.turnRight()
            }
        }
    }

    /**
     * Displays the current position [x, y, direction] of this Mower
     */
    fun getCurrentPosition() {
        println(position.getCurrentPos())
    }
}