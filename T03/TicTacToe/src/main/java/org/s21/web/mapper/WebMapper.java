package org.s21.web.mapper;

import org.s21.Config;
import org.s21.domain.model.Game;
import org.s21.domain.model.GameBoard;
import org.s21.web.model.GameBoardWeb;
import org.s21.web.model.GameWeb;

public class WebMapper {

  private static GameBoardWeb fromGameBoardToGameBoardWeb(GameBoard gameBoard) {
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

  public static GameWeb fromGameToGameWeb (Game game) {
    return new GameWeb(game.getUuid(), fromGameBoardToGameBoardWeb(game.getGameBoard()));
  }
}
