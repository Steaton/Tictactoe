package nl.trifork.tictactoe.ai.rulebasedplayer;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;

public interface Rule {
    Move applyRule(Square turn, Board board);
}
