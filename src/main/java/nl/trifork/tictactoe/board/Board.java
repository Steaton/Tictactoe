package nl.trifork.tictactoe.board;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.trifork.tictactoe.game.GameHasEndedException;
import nl.trifork.tictactoe.game.Move;

import java.util.Arrays;

public class Board implements Cloneable {

    protected Square[][] boardLayout = new Square[3][3];

    protected int numberOfRemainingMoves = 9;

    public Board() {
        initialiseEmptyBoardSquares();
    }

    private void initialiseEmptyBoardSquares() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                boardLayout[x][y] = Square.EMPTY;
            }
        }
    }

    public void takeTurn(Square square, int x, int y) {
        checkMoveIsValid(square, x, y);
        applyMoveToBoard(square, x, y);
        updateRemainingMoves();
    }

    private void updateRemainingMoves() {
        if (numberOfRemainingMoves == 0) {
            throw new GameHasEndedException("There are no more moves possible!");
        }
        numberOfRemainingMoves--;
    }

    public void takeTurn(Square square, Move move) {
        takeTurn(square, move.getX(), move.getY());
    }

    private void checkMoveIsValid(Square square, int x, int y) {
        checkValidSquareCoordinates(x, y);
        checkValidTurn(square);
        checkIfSquareAlreadyInUse(x, y);
    }

    private void applyMoveToBoard(Square square, int x, int y) {
        boardLayout[x - 1][y - 1] = square;
    }

    private void checkValidTurn(Square square) {
        checkTurnNotEmpty(square);
    }

    private void checkTurnNotEmpty(Square square) {
        if (square == Square.EMPTY) {
            throw new IllegalArgumentException("EMPTY cannot take a turn");
        }
    }



    private void checkIfSquareAlreadyInUse(int x, int y) {
        if (boardLayout[x - 1][y - 1] != Square.EMPTY) {
            throw new SquareAlreadyInUseException("Square already has value: " + boardLayout[x - 1][y - 1] + " at location [" + x + ", " + y + "]");
        }
    }

    private void checkValidSquareCoordinates(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IllegalArgumentException("Illegal Square Coordinates: [" + x + ", " + y + "]");
        }
    }

    public Square getSquare(int x, int y) {
        checkCoordinatesValid(x, y);
        return boardLayout[x - 1][y - 1];
    }

    private void checkCoordinatesValid(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IllegalArgumentException("X and Y must be between 1 and 3");
        }
    }

    @JsonGetter
    public Square[][] getBoardLayout() {
        return boardLayout;
    }

    @JsonGetter
    public int getNumberOfRemainingMoves() {
        return numberOfRemainingMoves;
    }

    public Board clone() throws CloneNotSupportedException {
        Board board = new Board();
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                board.setSquare(this.getSquare(x, y), x, y);
            }
        }
        return board;
    }

    private void setSquare(Square square, int x, int y) {
        boardLayout[x - 1][y - 1] = square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;

        return Arrays.deepEquals(boardLayout, board.boardLayout);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardLayout);
    }

    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
