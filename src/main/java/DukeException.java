package main.java;

public class DukeException
        extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}