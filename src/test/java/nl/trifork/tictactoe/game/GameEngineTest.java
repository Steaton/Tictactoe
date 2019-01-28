package nl.trifork.tictactoe.game;

import nl.trifork.tictactoe.ai.rulebasedplayer.RuleBasedComputerPlayer;
import nl.trifork.tictactoe.ai.rulebasedplayer.RuleHandler;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.cache.ActiveGames;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GameEngineTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RuleBasedComputerPlayer computerPlayer;

    private GameEngine gameEngine;

    @Before
    public void setup() {
        computerPlayer = new RuleBasedComputerPlayer();
        RuleHandler ruleHandler = new RuleHandler();
        computerPlayer.setRuleHandler(ruleHandler);
        gameEngine = new GameEngine();
        gameEngine.setComputerPlayer(computerPlayer);
    }

    @Test
    public void should_be_able_to_play_game() {
        // Given
        Game game = ActiveGames.newGame();

        // When
        game = gameEngine.makeMove(game, new Move(2, 2), Square.X);
        game = gameEngine.makeComputerMove(game, Square.O);

        // Then
        assertEquals(7, game.getBoard().getNumberOfRemainingMoves());
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
    }

    @Test
    public void should_be_able_to_win_with_X() {
        // Given
        Game game = ActiveGames.newGame();
        game.setBoard(BoardBuilder.aBoard()
                .withRow(1, "XX-")
                .withRow(2, "OO-"));

        // When
        game = gameEngine.makeMove(game, new Move(3, 1), Square.X);

        // Then
        assertEquals(GameStatus.X_WON, game.getGameStatus());
    }

    @Test
    public void should_be_able_to_win_with_O() {
        // Given
        Game game = ActiveGames.newGame();
        game.setBoard(BoardBuilder.aBoard()
                .withRow(1, "XX-")
                .withRow(2, "OO-"));

        // When
        game = gameEngine.makeMove(game, new Move(3,3), Square.X);
        game = gameEngine.makeMove(game, new Move(3, 2), Square.O);

        // Then
        assertEquals(GameStatus.O_WON, game.getGameStatus());
    }

    @Test
    public void should_be_able_to_draw() {
        // Given
        Game game = ActiveGames.newGame();
        game.setBoard(BoardBuilder.aBoard()
                .withRow(1, "XXO")
                .withRow(2, "OOX")
                .withRow(3, "XO-"));

        // When
        game = gameEngine.makeMove(game, new Move(3, 3), Square.X);

        // Then
        assertEquals(GameStatus.DRAW, game.getGameStatus());
    }

    @Test
    public void should_ensure_turn_not_played_out_of_order() {
        // Expect
        expectedException.expect(TurnPlayedOutOfOrderException.class);
        expectedException.expectMessage("It's not X's turn");

        // Given
        Game game = ActiveGames.newGame();

        // When
        gameEngine.makeMove(game, new Move(2, 2), Square.X);
        gameEngine.makeMove(game, new Move(2, 3), Square.X);
    }


}