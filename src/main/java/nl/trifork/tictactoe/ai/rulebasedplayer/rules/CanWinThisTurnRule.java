package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.ai.LookAheadProcessor;
import nl.trifork.tictactoe.ai.rulebasedplayer.Rule;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import nl.trifork.tictactoe.game.WinCalculator;

import java.util.Map;
import java.util.Set;

public class CanWinThisTurnRule implements Rule {

    @Override
    public Move applyRule(Square turn, Board board) {
        return getWinningMove(turn, board);
    }

    private Move getWinningMove(Square turn, Board board) {
        Map<Board, Move> allPossibleMoves = LookAheadProcessor.getAllPossibleMoves(turn, board);
        Set<Board> boards = allPossibleMoves.keySet();
        for (Board nextBoard : boards) {
            if (WinCalculator.hasPlayerWon(turn, nextBoard)) {
                return allPossibleMoves.get(nextBoard);
            }
        }
        return null;
    }
}
