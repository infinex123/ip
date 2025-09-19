package clover;

/**
 * Represents an exception specific to the Clover/Duke application.
 * Used to indicate errors in command execution, parsing, or task operations.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new {@code DukeException} with the specified detail message.
     *
     * @param msg the detail message explaining the cause of the exception
     */
    public DukeException(String msg) {
        super(msg);
    }
}

