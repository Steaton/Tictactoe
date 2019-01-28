package nl.trifork.tictactoe.game;

import nl.trifork.tictactoe.ai.LookAheadProcessor;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;

import java.util.Map;

public class WinCalculator {

    public static boolean hasPlayerWon(Square turn, Board board) {
        if (checkForWinningRow(turn, board) || checkForWinningColumn(turn, board) || checkForWinningDiagonal(turn, board)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDraw(Square turn, Board board) {
        if (board.getNumberOfRemainingMoves() == 0 && !hasPlayerWon(turn, board)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkForWinningRow(Square turn, Board board) {
        for (int y = 1; y < 4; y++) {
            if (isWinningRow(turn, board, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWinningRow(Square turn, Board board, int y) {
        return turn.equals(board.getSquare(1, y)) && turn.equals(board.getSquare(2, y)) && turn.equals(board.getSquare(3, y));
    }

    private static boolean checkForWinningColumn(Square turn, Board board) {
        for (int x = 1; x < 4; x++) {
            if (isWinningColumn(turn, board, x)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWinningColumn(Square turn, Board board, int x) {
        return turn.equals(board.getSquare(x, 1)) && turn.equals(board.getSquare(x, 2)) && turn.equals(board.getSquare(x, 3));
    }

    private static boolean checkForWinningDiagonal(Square turn, Board board) {
        if (turn.equals(board.getSquare(1, 1)) && turn.equals(board.getSquare(2,2)) && turn.equals(board.getSquare(3, 3))) {
            return true;
        } else if (turn.equals(board.getSquare(1, 3)) && turn.equals(board.getSquare(2, 2)) && turn.equals(board.getSquare(3, 1))) {
            return true;
        } else {
            return false;
        }
    }
}
