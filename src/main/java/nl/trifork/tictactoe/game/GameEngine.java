package nl.trifork.tictactoe.game;

import nl.trifork.tictactoe.ai.ComputerPlayer;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

    @Autowired
    private ComputerPlayer computerPlayer;

    public Game makeMove(Game game, Move move, Square turn) {
        checkIsValid(game, turn);
        Board board = game.getBoard();
        board.takeTurn(turn, move);
        updateGameStatus(game, move, turn, board);
        return game;
    }

    public Game makeComputerMove(Game game, Square turn) {
        checkIsValid(game, turn);
        Board board = game.getBoard();
        Move move = computerPlayer.selectNextMove(turn, board);
        board.takeTurn(turn, move);
        updateGameStatus(game, move, turn, board);
        return game;
    }

    private void updateGameStatus(Game game, Move move, Square turn, Board board) {
        game.setLastMove(move);
        determineIfGameHasEnded(game, turn, board);
        updateNextToPlay(game);
    }

    private void determineIfGameHasEnded(Game game, Square turn, Board board) {
        if (WinCalculator.hasPlayerWon(turn, board)) {
            if (turn == Square.X) {
                game.setGameStatus(GameStatus.X_WON);
            } else {
                game.setGameStatus((GameStatus.O_WON));
            }
        }
        if (WinCalculator.isDraw(turn, board)) {
            game.setGameStatus(GameStatus.DRAW);
        }
    }

    private void updateNextToPlay(Game game) {
        if (game.getNextToPlay() == Square.X) {
            game.setNextToPlay(Square.O);
        } else if (game.getNextToPlay() == Square.O) {
            game.setNextToPlay(Square.X);
        }
    }

    private void checkIsValid(Game game, Square turn) {
        checkGameIsInProgress(game);
        checkSqaureNotPlayedOutOfTurn(game, turn);
    }

    private void checkGameIsInProgress(Game game) {
        if (game.getGameStatus() != GameStatus.IN_PROGRESS) {
            throw new GameHasEndedException("This game has already ended no further moves are possible!");
        }
    }

    private void checkSqaureNotPlayedOutOfTurn(Game game, Square square) {
        if (square != game.getNextToPlay()) {
            throw new TurnPlayedOutOfOrderException("It's not " + square + "'s turn");
        }
    }

    public void setComputerPlayer(ComputerPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

}
