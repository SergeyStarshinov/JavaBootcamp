package org.s21.datasource.model;

import org.s21.Config;

public class GameBoardData {
  private final int[] gameBoard;

  public GameBoardData() {
    gameBoard = new int[Config.BOARD_SIZE * Config.BOARD_SIZE];
  }

  public int getCell(int pos) {
    return gameBoard[pos];
  }
  public void setCell(int pos, int val) {
    gameBoard[pos] = val;
  }

}
