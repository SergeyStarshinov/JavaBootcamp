package org.s21.web.mapper;

import org.s21.Config;
import org.s21.domain.model.Game;
import org.s21.domain.model.GameBoard;
import org.s21.web.model.GameBoardWeb;
import org.s21.web.model.GameWeb;

public class WebMapper {

  private static GameBoardWeb fromGameBoardToGameBoardWeb(GameBoard gameBoard) {
    if (gameBoard == null) return null;
    GameBoardWeb result = new GameBoardWeb();
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++j) {
        char c = switch (gameBoard.getCell(i, j)) {
          case Config.HUMAN_PLAYER -> Config.HUMAN_SIGN;
          case Config.AI_PLAYER -> Config.AI_SIGN;
          default -> Config.VOID_SIGN;
        };
        result.setCell(i, j, c);
      }
    }
    return result;
  }

  private static GameBoard fromGameBoardWebToGameBoard(GameBoardWeb gameBoardWeb) {
    if (gameBoardWeb == null) return null;
    GameBoard result = new GameBoard();
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++j) {
        char c = switch (gameBoardWeb.getCell(i, j)) {
          case Config.HUMAN_SIGN -> Config.HUMAN_PLAYER;
          case Config.AI_SIGN -> Config.AI_PLAYER;
          default -> Config.VOID_FIELD;
        };
        result.setCell(i, j, c);
      }
    }
    return result;
  }

  public static Game fromGameWebToGame (GameWeb gameWeb) {
    if (gameWeb == null) return null;
    return new Game(gameWeb.getUuid(), fromGameBoardWebToGameBoard(gameWeb.getGameBoard()));
  }

  public static GameWeb fromGameToGameWeb (Game game) {
    if (game == null) return new GameWeb(null, null);
    return new GameWeb(game.getUuid(), fromGameBoardToGameBoardWeb(game.getGameBoard()));
  }
}
