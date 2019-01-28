package nl.trifork.tictactoe.api;

public class InvalidGameIdException extends RuntimeException {
    public InvalidGameIdException(String message) {
        super(message);
    }
}
