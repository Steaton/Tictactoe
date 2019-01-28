package nl.trifork.tictactoe.ai;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LookAheadProcessorTest {

    @Test
    public void should_return_all_possible_moves_for_blank_board() throws CloneNotSupportedException {
        // Given
        Board board = BoardBuilder.aBoard();

        // When
        Map<Board, Move> allPossibleMoves = LookAheadProcessor.getAllPossibleMoves(Square.X, board);

        // Then
        assertTrue(allPossibleMoves.size() == 9);
    }

    @Test
    public void should_return_all_possible_moves_when_only_one_move_remains() throws CloneNotSupportedException {
        // Given
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOX")
                .withRow(2, "OXO")
                .withRow(3, "OX-");

        // When
        Map<Board, Move> allPossibleBoards = LookAheadProcessor.getAllPossibleMoves(Square.O, board);

        // Then
        Board expectedBoard = BoardBuilder.aBoard()
                .withRow(1, "XOX")
                .withRow(2, "OXO")
                .withRow(3, "OXO");
        assertTrue(allPossibleBoards.size() == 1);
        assertTrue(allPossibleBoards.containsKey(expectedBoard));
        assertEquals(new Move(3, 3), allPossibleBoards.get(expectedBoard));
    }

    @Test
    public void should_return_empty_list_if_game_finished() throws CloneNotSupportedException {
        // Given
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOX")
                .withRow(2, "OXO")
                .withRow(3, "OXO");

        // When
        Map<Board, Move> allPossibleMoves = LookAheadProcessor.getAllPossibleMoves(Square.O, board);

        // Then
        assertTrue(allPossibleMoves.size() == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_the_empty_square_as_the_turn() throws CloneNotSupportedException {
        // When
        LookAheadProcessor.getAllPossibleMoves(Square.EMPTY, BoardBuilder.aBoard());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_null_board() throws CloneNotSupportedException {
        // When
        LookAheadProcessor.getAllPossibleMoves(Square.O, null);
    }
}