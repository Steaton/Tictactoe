package nl.trifork.tictactoe.ai.rulebasedplayer;

import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RuleBasedComputerPlayerTest {

    @Mock
    private RuleHandler mockRuleHandler;

    @InjectMocks
    private RuleBasedComputerPlayer ruleBasedComputerPlayer;

    @Test
    public void should_call_rules_handler() {
        // Given
        BoardBuilder board = BoardBuilder.aBoard();

        // When
        ruleBasedComputerPlayer.selectNextMove(Square.O, board);

        // Then
        verify(mockRuleHandler).applyRulesToDetermineNextMove(eq(Square.O), eq(board));
    }

}