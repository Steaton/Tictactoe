package nl.trifork.tictactoe.board;

public class SquareAlreadyInUseException extends RuntimeException {
    public SquareAlreadyInUseException(String message) {
        super(message);
    }
}
