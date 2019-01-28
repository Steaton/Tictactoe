package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PreferCenterSquareRuleTest {

    @Test
    public void should_select_empty_center_square() {
        // Given
        PreferCenterSquareRule rule = new PreferCenterSquareRule();
        Board board = BoardBuilder.aBoard()
                .withRow(1, "X--");

        // When
        Move move = rule.applyRule(Square.O, board);

        // Then
        assertEquals(new Move(2, 2), move);
    }

    @Test
    public void should_return_null_if_center_square_taken() {
        // Given
        PreferCenterSquareRule rule = new PreferCenterSquareRule();
        Board board = BoardBuilder.aBoard()
                .withRow(2, "-X-");

        // When
        Move move = rule.applyRule(Square.O, board);

        // Then
        assertNull(move);
    }
}