package nl.trifork.tictactoe.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.trifork.tictactoe.game.Move;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_blank_board_on_initialisation() throws JsonProcessingException {
        // When
        Board board = BoardBuilder.aBoard();

        // Then
        assertEquals(new Board(), board);
    }

    @Test
    public void should_throw_exception_when_making_move_outside_grid() {
        // Expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Illegal Square Coordinates: [0, 1]");

        // Given
        Board board = BoardBuilder.aBoard();

        // When
        board.takeTurn(Square.X, 0, 1);
    }

    @Test
    public void should_not_allow_an_empty_turn() {
        // Expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("EMPTY cannot take a turn");

        // Given
        Board board = BoardBuilder.aBoard();

        // When
        board.takeTurn(Square.EMPTY, 1, 1);
    }

    @Test
    public void should_not_allow_turn_on_square_if_already_used() {
        // Expect
        expectedException.expect(SquareAlreadyInUseException.class);
        expectedException.expectMessage("Square already has value: X at location [1, 1]");

        // Given
        Board board = BoardBuilder.aBoard();
        board.takeTurn(Square.X, 1, 1);

        // When
        board.takeTurn(Square.O, 1, 1);
    }
    
    @Test
    public void should_throw_exception_if_getting_invalid_square() {
        // Expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("X and Y must be between 1 and 3");
        
        // Given
        Board board = BoardBuilder.aBoard();
        
        // When
        board.getSquare(1, 0);
    }

    @Test
    public void should_allow_multiple_moves_to_be_made() {
        // Given
        Board board = BoardBuilder.aBoard();

        // When
        board.takeTurn(Square.X, new Move(2,2));
        board.takeTurn(Square.O, new Move(1,1));
        board.takeTurn(Square.X, new Move(1,2));
        board.takeTurn(Square.O, new Move(3,2));

        // Then
        Board expected = BoardBuilder.aBoard()
                .withRow(1, "O--")
                .withRow(2, "XXO")
                .withRow(3, "---");
        assertEquals(expected, board);
    }

    @Test
    public void should_clone_a_board() throws CloneNotSupportedException {
        // Given
        Board board = BoardBuilder.aBoard()
                .withRow(1, "O--")
                .withRow(2, "XXO")
                .withRow(3, "---");

        // When
        Board clone = board.clone();

        // Then
        assertFalse(board == clone);
        assertEquals(board, clone);
    }

    @Test
    public void should_print_out_json_from_to_string() {
        // Given
        Board board = BoardBuilder.aBoard()
                .withRow(1, "O--")
                .withRow(2, "XXO")
                .withRow(3, "---");

        // When
        String string = board.toString();

        // Then
        String expected = "{\r\n" +
                "  \"boardLayout\" : [ [ \"O\", \"X\", \"EMPTY\" ], [ \"EMPTY\", \"X\", \"EMPTY\" ], [ \"EMPTY\", \"O\", \"EMPTY\" ] ],\r\n" +
                "  \"numberOfRemainingMoves\" : 5\r\n" +
                "}";
        assertEquals(expected, string);
    }
}