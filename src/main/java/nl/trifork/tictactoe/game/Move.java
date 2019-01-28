package nl.trifork.tictactoe.game;

public class Move {
    private final int y;
    private final int x;

    public Move(int x, int y) {
        checkCoordinatesValid(x, y);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void checkCoordinatesValid(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IllegalArgumentException("X and Y must be between 1 and 3");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;

        Move move = (Move) o;

        if (y != move.y) return false;
        return x == move.x;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }
}
