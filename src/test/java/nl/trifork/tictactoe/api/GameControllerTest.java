package nl.trifork.tictactoe.api;

import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.BoardBuilder;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.game.Game;
import nl.trifork.tictactoe.game.GameEngine;
import nl.trifork.tictactoe.game.Move;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameEngine mockGameEngine;

    @InjectMocks
    private GameController gameController;

    @Test
    public void should_start_a_new_game() {
        // When
        Game game = gameController.startNewGame();

        // Then
        assertEquals(BoardBuilder.aBoard(), game.getBoard());
    }

    @Test
    public void should_execute_a_specific_move() {
        // Given
        Game game = gameController.startNewGame();
        when(mockGameEngine.makeMove(eq(game), eq(new Move(2, 2)), eq(Square.X)))
                .thenReturn(new Game(1234, Square.X));


        // When
        Game updated = gameController.move(game.getGameId(), Square.X, 2, 2);

        // Then
        verify(mockGameEngine).makeMove(eq(game), eq(new Move(2, 2)), eq(Square.X));
        assertTrue(updated.getGameId() == 1234);
    }

    @Test
    public void should_execute_a_computer_move() {
        // Given
        Game game = gameController.startNewGame();
        when(mockGameEngine.makeComputerMove(eq(game), eq(Square.X)))
                .thenReturn(new Game(1234, Square.X));


        // When
        Game updated = gameController.computerMove(game.getGameId(), Square.X);

        // Then
        verify(mockGameEngine).makeComputerMove(eq(game), eq(Square.X));
        assertTrue(updated.getGameId() == 1234);
    }
}