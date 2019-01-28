package nl.trifork.tictactoe.ai;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Move;

public interface ComputerPlayer {
    Move selectNextMove(Square turn, Board board);
}
