package nl.trifork.tictactoe.cache;

import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * This uses a static map to hold a list of active games.
 * This is not good enough for production purposes, but is a ploaceholder for
 * a more complete persistence solution.
 */
public class ActiveGames {

    private static Map<Integer, Game> gamesList = new ConcurrentHashMap<Integer, Game>();

    public static Game newGame() {
        int id = GameIdGenerator.next();
        Game game = new Game(id, Square.X);
        gamesList.put(id, game);
        return game;
    }

    public static Game getGame(Integer id) {
        return (Game) gamesList.get(id);
    }
}
