package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SelectRandomMoveRuleTest {

    @Test
    public void should_select_a_move_at_random_when_moves_are_available() {
        // Given
        SelectRandomMoveRule rule = new SelectRandomMoveRule();
        Board board = BoardBuilder.aBoard();

        // When
        Move move = rule.applyRule(Square.X, board);

        // Then
        assertNotNull(move);
    }

    @Test
    public void should_select_only_remaining_move() {
        // Given
        SelectRandomMoveRule rule = new SelectRandomMoveRule();
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOX")
                .withRow(2, "XOX")
                .withRow(3, "O-O");

        // When
        Move move = rule.applyRule(Square.X, board);

        // Then
        assertEquals(new Move(2, 3), move);
    }
}