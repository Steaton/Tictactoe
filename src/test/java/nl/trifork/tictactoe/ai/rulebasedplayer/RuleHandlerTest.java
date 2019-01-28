package nl.trifork.tictactoe.ai.rulebasedplayer;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RuleHandlerTest {

    private RuleHandler ruleHandler = new RuleHandler();

    @Test
    public void should_call_set_of_rules() {
        // Given
        Board board = BoardBuilder.aBoard().withRow(1, "X--")
                .withRow(2, "OOX")
                .withRow(3, "---");

        // When
        Move move = ruleHandler.applyRulesToDetermineNextMove(Square.X, board);

        // Then
        assertNotNull(move);
    }
}