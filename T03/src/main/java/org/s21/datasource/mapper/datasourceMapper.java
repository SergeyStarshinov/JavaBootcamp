package org.s21.datasource.mapper;

import org.s21.Config;
import org.s21.datasource.model.GameBoardData;
import org.s21.domain.model.GameBoard;

public class datasourceMapper {

  public static GameBoardData fromGameBoardToGameBoardDate(GameBoard gameBoard) {
    GameBoardData result = new GameBoardData();
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++j) {
        result.setCell(i * Config.BOARD_SIZE + j, gameBoard.getCell(i, j));
      }
    }
    return result;
  }

  public static GameBoard fromGameBoardDateToGameBoard(GameBoardData gameBoardDate) {
    GameBoard result = new GameBoard();
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++j) {
        result.setCell(i, j, gameBoardDate.getCell(i * Config.BOARD_SIZE + j));
      }
    }
    return result;
  }
}
