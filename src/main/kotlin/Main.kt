import java.io.File
import kotlin.system.exitProcess

/**
 * Main method, entry point of the program, checks for the arguments
 */
fun main(args: Array<String>) {
    if (args.size != 1 || args[0].isNullOrBlank()) {
        println("Usage: mowItNow path/to/instructions/file")
        exitProcess(1)
    }

    try {
        MowItNow(fileReader(args[0])).start()
    } catch (e: InvalidInstructionException) {
        logErrorAndQuit(e.message.toString())
    }
}

/**
 * Reads the file at the given path, and returns it as a String
 */
fun fileReader(path: String): String {
    var file = File(path)

    if (!file.exists()) {
        logErrorAndQuit("Couldn't find $path")
    } else if (!file.canRead()) {
        logErrorAndQuit("Not allowed to read file $path")
    }
    return file.readText()
}

/**
 * Simply logs an error
 * @param message String error to log
 */
fun logError(message: String) {
    System.err.println("Error : $message")
}

/**
 * Logs an error and quit the program
 * @param message String error to log before exiting
 */
fun logErrorAndQuit(message: String) {
    logError(message)
    System.err.println("Terminating...");
    exitProcess(1)
}

/**
 * Custom Exception allowing to handle errors in the input file
 */
class InvalidInstructionException(message: String): Exception(message)