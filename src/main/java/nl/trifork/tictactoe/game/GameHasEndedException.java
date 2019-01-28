package nl.trifork.tictactoe.game;

public class GameHasEndedException extends RuntimeException {
    public GameHasEndedException(String message) {
        super(message);
    }
}
