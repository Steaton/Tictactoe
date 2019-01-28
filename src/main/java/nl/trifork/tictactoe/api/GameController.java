package nl.trifork.tictactoe.api;

import nl.trifork.tictactoe.ai.ComputerPlayer;
import nl.trifork.tictactoe.board.Board;
import nl.trifork.tictactoe.board.Square;
import nl.trifork.tictactoe.cache.ActiveGames;
import nl.trifork.tictactoe.game.Game;
import nl.trifork.tictactoe.game.GameEngine;
import nl.trifork.tictactoe.game.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    private GameEngine gameEngine;

    @GetMapping("/startNewGame")
    public Game startNewGame() {
        return ActiveGames.newGame();
    }

    @PostMapping("/executeTurn")
    public @ResponseBody Game move(@RequestParam Integer gameId, @RequestParam Square turn, @RequestParam int column, @RequestParam int row) {
        Game game = ActiveGames.getGame(gameId);
        checkGameIsActive(gameId, game);
        return gameEngine.makeMove(game, new Move(column, row), turn);
    }

    @PostMapping("/executeComputerTurn")
    public Game computerMove(@RequestParam Integer gameId, @RequestParam Square turn) {
        Game game = ActiveGames.getGame(gameId);
        checkGameIsActive(gameId, game);
        return gameEngine.makeComputerMove(game, turn);
    }

    private void checkGameIsActive(@RequestParam Integer gameId, Game game) {
        if (game == null) {
            throw new InvalidGameIdException("The game with gameId: " + gameId + " does not exist!");
        }
    }
}
