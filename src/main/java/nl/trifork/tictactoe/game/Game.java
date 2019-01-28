package nl.trifork.tictactoe.game;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;

public class Game {

    private int gameId;

    private Board board;

    private Move lastMove;

    private GameStatus gameStatus;

    private Square nextToPlay = Square.X;

    public Game(int gameId, Square nextToPlay) {
        this.gameId = gameId;
        this.board = new Board();
        this.lastMove = null;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public int getGameId() {
        return gameId;
    }

    public Board getBoard() {
        return board;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Square getNextToPlay() {
        return nextToPlay;
    }

    public void setNextToPlay(Square nextToPlay) {
        this.nextToPlay = nextToPlay;
    }
}
