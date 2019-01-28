package nl.trifork.tictactoe.ai;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;

import java.util.HashMap;
import java.util.Map;

public class LookAheadProcessor {

    public static Map<Board, Move> getAllPossibleMoves(Square turn, Board board) {
        checkInputParameters(turn, board);
        Map<Board, Move> possibleMoves = new HashMap<>();
        try {
            examinePossibleMoves(turn, board, possibleMoves);
        } catch (CloneNotSupportedException e) {
            // Not possible, ignore.
        }
        return possibleMoves;
    }

    private static void examinePossibleMoves(Square turn, Board board, Map<Board, Move> possibleMoves) throws CloneNotSupportedException {
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (board.getSquare(x, y).equals(Square.EMPTY)) {
                    addPossibleMove(turn, board, possibleMoves, x, y);
                }
            }
        }
    }

    private static void addPossibleMove(Square turn, Board board, Map<Board, Move> possibleMoves, int x, int y) throws CloneNotSupportedException {
        Board clone = board.clone();
        clone.takeTurn(turn, x, y);
        possibleMoves.put(clone, new Move(x, y));
    }

    private static void checkInputParameters(Square turn, Board board) {
        if (turn == Square.EMPTY) throw new IllegalArgumentException("Must be either X's turn or O's turn");
        if (board == null) throw new IllegalArgumentException("Board cannot be null");
    }
}
