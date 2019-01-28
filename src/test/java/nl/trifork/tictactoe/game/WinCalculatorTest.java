package nl.trifork.tictactoe.game;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import org.junit.Test;

import static org.junit.Assert.*;

public class WinCalculatorTest {

    @Test
    public void should_detect_a_winning_row() {
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOO")
                .withRow(2, "XXX")
                .withRow(3, "OO-");
        assertTrue(WinCalculator.hasPlayerWon(Square.X, board));
        assertFalse(WinCalculator.hasPlayerWon(Square.O, board));
    }

    @Test
    public void should_detect_a_winning_column() {
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOO")
                .withRow(2, "XOX")
                .withRow(3, "-O-");
        assertTrue(WinCalculator.hasPlayerWon(Square.O, board));
        assertFalse(WinCalculator.hasPlayerWon(Square.X, board));
    }

    @Test
    public void should_detect_a_winning_diagonal_forward_slash() {
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOO")
                .withRow(2, "XOX")
                .withRow(3, "O--");
        assertTrue(WinCalculator.hasPlayerWon(Square.O, board));
        assertFalse(WinCalculator.hasPlayerWon(Square.X, board));
    }

    @Test
    public void should_detect_a_winning_diagonal_backslash() {
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOO")
                .withRow(2, "XXO")
                .withRow(3, "OOX");
        assertTrue(WinCalculator.hasPlayerWon(Square.X, board));
        assertFalse(WinCalculator.hasPlayerWon(Square.O, board));
    }

    @Test
    public void blank_board_should_not_have_any_winner() {
        Board board = BoardBuilder.aBoard();
        assertFalse(WinCalculator.hasPlayerWon(Square.X, board));
        assertFalse(WinCalculator.hasPlayerWon(Square.O, board));
    }

    @Test
    public void drawing_board_should_not_have_any_winner() {
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOX")
                .withRow(2, "XXO")
                .withRow(3, "OXO");
        assertFalse(WinCalculator.hasPlayerWon(Square.X, board));
        assertFalse(WinCalculator.hasPlayerWon(Square.O, board));
    }
}