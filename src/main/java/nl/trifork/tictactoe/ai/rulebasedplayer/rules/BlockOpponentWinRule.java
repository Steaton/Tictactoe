package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.ai.LookAheadProcessor;
import nl.trifork.tictactoe.ai.rulebasedplayer.Rule;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;
import nl.trifork.tictactoe.game.WinCalculator;

import java.util.Map;
import java.util.Set;

public class BlockOpponentWinRule implements Rule {

    @Override
    public Move applyRule(Square turn, Board board) {
        return getBlockingMove(turn, board);
    }

    private Move getBlockingMove(Square turn, Board board) {
        Square opponentTurn = getOpponentsTurn(turn);
        Map<Board, Move> allPossibleMoves = LookAheadProcessor.getAllPossibleMoves(opponentTurn, board);
        Set<Board> boards = allPossibleMoves.keySet();
        for (Board nextBoard : boards) {
            if (WinCalculator.hasPlayerWon(opponentTurn, nextBoard)) {
                return allPossibleMoves.get(nextBoard);
            }
        }
        return null;
    }

    private Square getOpponentsTurn(Square turn) {
        Square opponentTurn;
        if (turn == Square.O) opponentTurn = Square.X;
        else opponentTurn = Square.O;
        return opponentTurn;
    }
}
