import kotlin.system.exitProcess

/**
 * Interprets the instructions, creates the Mover objects, move them around the lawn
 * @param content String containing the content of the input file
 */
class MowItNow(content: String) {

    val mowers :ArrayList<Mower>

    /**
     * Initializer of the object, launches the program core :
     * reads the instructions and creates the mowers
     */
    init {
        mowers = interpretFileContent(content)

    }

    /**
     * Makes each mower of the ArrayList follow the instructions and print their position
     */
    fun start() {
        mowers.forEach {
            it.proceedInstructions()
            it.getCurrentPosition()
        }
    }

    /**
     * Translates the content of the input files into Mower objects able to process the given instructions.
     * In case of a wrong format, Exceptions may be thrown
     * @param content String containing the content of the input file
     * @return ArrayList<Mower> the list of mowers as described in the input file
     */
    private fun interpretFileContent(content: String): ArrayList<Mower> {
        // stores each non-empty line as an element of an ArrayList
        var instructions = ArrayList(content.split('\n')).filter { !it.isNullOrBlank() }
        var mowers = ArrayList<Mower>()

        // checks whether the file has a correct amount of instruction lines
        if (instructions.size % 2 != 1 || instructions.size < 3) {
            val size = instructions.size
            throw InvalidInstructionException("The file has $size lines of instructions but should contain an odd" +
                    " number of non-empty lines of instructions, minimum 3.")
        }

        // gets the line of the lawn's properties, checks whether it has the expected format
        val (mapWidth, mapHeight, dir) = getCoord(instructions[0])
        if (dir != ' ') {
            val line = instructions[0]
            throw InvalidInstructionException("Lawn's properties line \"$line\" cannot contain anything" +
                    " else than 2 numbers")
        }

        // all the other pairs of lines are considered as mowers, each are created and stored in an ArrayList
        for (i in 1 until instructions.size step 2) {
            var position = Position(getCoord(instructions[i]), Pair(mapWidth, mapHeight))
            mowers.add(Mower(position, instructions[i + 1]))
        }

        return mowers
    }

    /**
     * Extracts the coordinates from a line.
     * Only triggers an exception if the x or y is missing.
     * The missing direction is handled in the {@see Position} object
     * @param line String containing the coordinates to extract
     * @return Triple<Int, Int, Char> the coordinates x, y, direction
     */
    private fun getCoord(line: String): Triple<Int, Int, Char> {
        val coord = line.split(" ")
        val x: Int
        val y: Int
        val dir: Char

        try {
            x = coord[0].toInt()
            y = coord[1].toInt()
            dir = if (coord.size > 2 && coord.isNotEmpty() && coord[2].length == 1) coord[2][0] else ' '
        } catch (e: NumberFormatException) {
            throw InvalidInstructionException("Coordinates \"$line\" must consist in two Int followed by a char")
        } catch (e: IndexOutOfBoundsException) {
            throw InvalidInstructionException("Coordinates \"$line\" must be in format \"x y [direction]\"")
        }

        return Triple(x, y, dir)
    }
}