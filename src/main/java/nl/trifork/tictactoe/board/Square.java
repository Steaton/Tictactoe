package nl.trifork.tictactoe.board;

public enum Square {
    EMPTY("-"), X("X"), O("O");

    private final String label;

    private Square(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
