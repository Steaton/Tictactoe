package nl.trifork.tictactoe.board;

/**
 * Utility for simplifying test cases (non-standard builder pattern using inheritance).
 */
public class BoardBuilder extends Board {

    public BoardBuilder() {
        super();
    }

    public static BoardBuilder aBoard() {
        return new BoardBuilder();
    }

    public BoardBuilder withRow(int y, String row) {
        checkValidRow(y, row);
        for (int x = 0; x < 3; x++) {
            if ('X' == row.charAt(x)) {
                boardLayout[x][y - 1] = Square.X;
            } else if ('O' == row.charAt(x)) {
                boardLayout[x][y - 1] = Square.O;
            }
        }
        this.numberOfRemainingMoves = countEmptySquares();
        return this;
    }

    private int countEmptySquares() {
        int count = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (boardLayout[x][y] == Square.EMPTY) {
                    count++;
                }
            }
        }
        return count;
    }

    private void checkValidRow(int y, String row) {
        if (y < 1 || y > 3)
            throw new IllegalArgumentException("Must be a row number between 1 & 3");

        if (row.length() != 3)
            throw new IllegalArgumentException("Must be 3 characters e.g. XOO or -X- to represent a the contents of the row");
    }
}
