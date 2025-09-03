package org.s21.web.controller;

import org.s21.Config;
import org.s21.datasource.service.DatasourceService;
import org.s21.domain.model.Game;
import org.s21.domain.service.GameService;
import org.s21.web.mapper.WebMapper;
import org.s21.web.model.GameWeb;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class WebController {

  public record Coordinates (int row, int col) {
  }

  private final GameService gameService;

  private final DatasourceService datasourceService;

  public WebController(GameService gameService, DatasourceService datasourceService) {
    this.gameService = gameService;
    this.datasourceService = datasourceService;
  }

  @GetMapping("/new_game_player")
  public GameWeb newGamePlayer() {
    Game newGame = gameService.newGame(Config.HUMAN_PLAYER);
    datasourceService.saveGame(newGame);
    return WebMapper.fromGameToGameWeb(newGame);
  }

  @GetMapping("/new_game_ai")
  public GameWeb newGameAi() {
    Game newGame = gameService.newGame(Config.AI_PLAYER);
    datasourceService.saveGame(newGame);
    return WebMapper.fromGameToGameWeb(newGame);
  }

  @PostMapping("/{uuid}")
  public GameWeb playerMove(@PathVariable UUID uuid, @RequestBody Coordinates coordinates) {
    int row = coordinates.row;
    int col = coordinates.col;
    Game game = datasourceService.loadGame(uuid);
    GameWeb result;

    if (game.getGameBoard() != null) {
      result = WebMapper.fromGameToGameWeb(game);
    } else {
      result = WebMapper.fromGameToGameWeb(new Game());
      result.setStatusMessage("Game with ID = " + uuid + " not found");
      return result;
    }

    if (row < 0 || col < 0 || row >= Config.BOARD_SIZE || col >= Config.BOARD_SIZE){
      result.setStatusMessage("Incorrect move coordinates");
      return result;
    }

    game.setCell(row, col, Config.HUMAN_PLAYER);

    if (!gameService.isValid(game)) {
      result.setStatusMessage("Incorrect move");
      return result;
    }

    result = WebMapper.fromGameToGameWeb(game);

    int isGameEnd = gameService.endGame(game.getGameBoard());
    if (isGameEnd == Config.HUMAN_PLAYER) {
      result.setStatusMessage("Game over. Player wins");
      datasourceService.deleteGame(uuid);
      return result;
    }
    if (isGameEnd == Config.DRAW) {
      result.setStatusMessage("It's a draw");
      datasourceService.deleteGame(uuid);
      return result;
    }

    gameService.nextMove(game.getGameBoard());
    datasourceService.saveGame(game);
    result = WebMapper.fromGameToGameWeb(game);

    isGameEnd = gameService.endGame(game.getGameBoard());
    if (isGameEnd == Config.AI_PLAYER) {
      result.setStatusMessage("Game over. AI wins");
      datasourceService.deleteGame(uuid);
    } else if (isGameEnd == Config.DRAW) {
      result.setStatusMessage("It's a draw");
      datasourceService.deleteGame(uuid);
    } else {
      result.setStatusMessage("Player's turn");
    }

    return result;
  }
}
