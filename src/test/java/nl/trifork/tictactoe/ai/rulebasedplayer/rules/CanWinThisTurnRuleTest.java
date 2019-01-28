package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CanWinThisTurnRuleTest {

    private CanWinThisTurnRule rule;

    @Before
    public void setup() {
        rule = new CanWinThisTurnRule();
    }

    @Test
    public void should_select_winning_move() {
        // Given
        Board board = BoardBuilder.aBoard()
                .withRow(1, "-OX")
                .withRow(2, "OXO")
                .withRow(3, "---");

        // When
        Move move = rule.applyRule(Square.X, board);

        // Then
        assertEquals(new Move(1,3), move);
    }

    @Test
    public void should_select_a_winning_move_when_multiple_moves_win() {
        // Given
        Board board = BoardBuilder.aBoard()
                .withRow(1, "XOX")
                .withRow(2, "OXO")
                .withRow(3, "-O-");

        // When
        Move move = rule.applyRule(Square.X, board);
        // Then
        assertTrue(new Move(1, 3).equals(move) ||
                new Move(3, 3).equals(move));
    }

    @Test
    public void should_return_null_for_blank_board_as_no_win_possible() {
        // Given
        Board board = BoardBuilder.aBoard();

        // When
        Move move = rule.applyRule(Square.X, board);

        // Then
        assertNull(move);
    }

}