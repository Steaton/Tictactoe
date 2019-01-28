package nl.trifork.tictactoe.cache;

public class GameIdGenerator {

    private static int nextId = 0;

    public static int next() {
        int id = nextId;
        nextId++;
        return id;
    }
}
