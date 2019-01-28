package nl.trifork.tictactoe.cache;

import nl.trifork.tictactoe.game.Game;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActiveGamesTest {

    @Test
    public void should_start_a_new_game_and_include_game_in_active_games_cache() {
        // When
        Game game = ActiveGames.newGame();

        // Then
        assertEquals(game, ActiveGames.getGame(game.getGameId()));
    }

}