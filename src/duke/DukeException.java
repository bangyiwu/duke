package duke;

/**
 * The DukeException is a custom exception class for Duke
 */
public class DukeException
        extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}