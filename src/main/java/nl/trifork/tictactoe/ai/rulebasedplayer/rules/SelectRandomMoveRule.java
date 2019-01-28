package nl.trifork.tictactoe.ai.rulebasedplayer.rules;

import nl.trifork.tictactoe.ai.LookAheadProcessor;
import nl.trifork.tictactoe.ai.rulebasedplayer.Rule;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;

import java.util.Map;
import java.util.Random;

public class SelectRandomMoveRule implements Rule {

    @Override
    public Move applyRule(Square turn, Board board) {
        Map<Board, Move> allPossibleMoves = LookAheadProcessor.getAllPossibleMoves(turn, board);
        int randomSelection = generateRandomNumber(allPossibleMoves.size());
        return (Move) allPossibleMoves.values().toArray()[randomSelection];
    }

    private int generateRandomNumber(int size) {
        Random rand = new Random();
        return rand.nextInt(size);
    }
}
