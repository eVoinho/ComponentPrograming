package sudoku.exception;

public class BadFieldValueException extends IllegalAccessError {
    public BadFieldValueException(final String message) {
        super(message);
    }
}
