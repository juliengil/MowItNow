import kotlin.system.exitProcess


/**
 * Position class defining the coordinates and direction of an element and its boundaries
 * @param coord Triple<Int, Int, Char> defining respectively the x, y coordinates, and the direction
 * @param map Pair<Int, Int> the x, y coordinates of the boundaries
 */
class Position(coord: Triple<Int, Int, Char>, map: Pair<Int, Int>) {

    // position and direction
    var x = coord.first
        private set
    var y = coord.second
        private set
    var dir = coord.third
        private set

    // map size
    private val xMax = map.first
    private val yMax = map.second

    /**
     * Checks for the validity of the inputs, otherwise throws an exception
     */
    init {
        if (xMax < 0 || yMax < 0)
            throw InvalidInstructionException("The lawn's boundaries \"$xMax : $yMax\" cannot be negative")
        else if (x !in 0..xMax)
            throw InvalidInstructionException("Horizontal position value \"$x\" must be between 0 and given width $xMax")
        else if (y !in 0..yMax)
            throw InvalidInstructionException("Vertical position value \"$y\" must be between 0 and given height $yMax")
        else if (!"NESW".contains(dir))
            throw InvalidInstructionException("Direction \"$dir\" must be either N, E, S or W")
    }

    /**
     * Handles the right turn for a mower, based on its current direction.
     */
    fun turnRight() {
        when (dir) {
            'N' -> dir = 'E'
            'E' -> dir = 'S'
            'S' -> dir = 'W'
            'W' -> dir = 'N'
        }
    }

    /**
     * Handles the left turn for a mower, based on its current direction.
     */
    fun turnLeft() {
        when (dir) {
            'N' -> dir = 'W'
            'E' -> dir = 'N'
            'S' -> dir = 'E'
            'W' -> dir = 'S'
        }
    }

    /**
     * Allows the mower to move towards its direction,
     * only if it doesn't take it outside of the map boundaries (if/else statements)
     */
    fun goForward() {
        when (dir) {
            'E' -> x += if (x == xMax) 0 else 1
            'S' -> y -= if (y == 0) 0 else 1
            'W' -> x -= if (x == 0) 0 else 1
            'N' -> y += if (y == yMax) 0 else 1
        }
    }

    /**
     * Returns the mower's current (x:y) position and direction as a String
     * @return String current position of the mower
     */
    fun getCurrentPos(): String {
        return "$x $y $dir"
    }

}