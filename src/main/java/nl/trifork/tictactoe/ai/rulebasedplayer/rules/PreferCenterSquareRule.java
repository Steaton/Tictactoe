package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.ai.rulebasedplayer.Rule;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;

public class PreferCenterSquareRule implements Rule {

    @Override
    public Move applyRule(Square turn, Board board) {
        if (board.getSquare(2, 2) == Square.EMPTY) {
            return new Move(2, 2);
        } else {
            return null;
        }
    }
}
