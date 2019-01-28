package nl.trifork.tictactoe.game;

public class TurnPlayedOutOfOrderException extends RuntimeException {
    public TurnPlayedOutOfOrderException(String message) {
        super(message);
    }
}
