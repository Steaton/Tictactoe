package nl.trifork.tictactoe.ai.rulebasedplayer;

import nl.trifork.tictactoe.ai.rulebasedplayer.rules.BlockOpponentWinRule;
import nl.trifork.tictactoe.ai.rulebasedplayer.rules.CanWinThisTurnRule;
import nl.trifork.tictactoe.ai.rulebasedplayer.rules.PreferCenterSquareRule;
import nl.trifork.tictactoe.ai.rulebasedplayer.rules.SelectRandomMoveRule;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleHandler {

    public Move applyRulesToDetermineNextMove(Square turn, Board board) {
        List<Rule> rules = loadSequenceOfRules();
        return applyRulesAndReturnFirstAnswer(rules, board, turn);
    }

    private Move applyRulesAndReturnFirstAnswer(List<Rule> rules, Board board, Square turn) {
        for (Rule rule : rules) {
            Move move = rule.applyRule(turn, board);
            if (move != null) return move;
        }
        throw new NoMoveDeterminedException();
    }

    private List<Rule> loadSequenceOfRules() {
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new PreferCenterSquareRule());
        rules.add(new CanWinThisTurnRule());
        rules.add(new BlockOpponentWinRule());
        rules.add(new SelectRandomMoveRule());
        return rules;
    }
}
