package sudoku.exception;

public class PieceSizeException extends IllegalAccessError {
    public PieceSizeException(final String message) {
        super(message);
    }
}
