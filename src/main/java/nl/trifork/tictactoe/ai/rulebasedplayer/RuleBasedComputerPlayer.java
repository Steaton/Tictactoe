package nl.trifork.tictactoe.ai.rulebasedplayer;

import nl.trifork.tictactoe.ai.ComputerPlayer;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * As opposed to selecting a MinMax algorithm that can always win (or draw) I've opted for a very basic rule based player.
 *
 * It is not expected to be unbeatable far from it, but is just going to apply some very basic rulebasedplayer of thumb to play in a
 * way that is fun. I'll assume this is intended for play with young(ish) children for instance, otherwise it is the most
 * boring game ever.
 */
@Component
public class RuleBasedComputerPlayer implements ComputerPlayer {

    @Autowired
    private RuleHandler ruleHandler;

    @Override
    public Move selectNextMove(Square turn, Board board) {
        return ruleHandler.applyRulesToDetermineNextMove(turn, board);
    }

    public void setRuleHandler(RuleHandler ruleHandler) {
        this.ruleHandler = ruleHandler;
    }
}
