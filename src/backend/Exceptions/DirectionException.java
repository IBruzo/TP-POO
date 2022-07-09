package backend.Exceptions;

public class DirectionException extends RuntimeException {
    private static final String MESSAGE = "The figure must be draw from top left edge to the bottom right edge";

    public DirectionException() {
        super(MESSAGE);
    }
}
