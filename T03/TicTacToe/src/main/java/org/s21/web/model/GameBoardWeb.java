package org.s21.web.model;

import org.s21.Config;

public class GameBoardWeb {
  private final char[][] gameBoard;

  public GameBoardWeb() {
    this.gameBoard = new char[Config.BOARD_SIZE][Config.BOARD_SIZE];
  }

  public void setCell(int row, int col, char val) {
    gameBoard[row][col] = val;
  }

  public char[][] getGameBoard() {
    return gameBoard;
  }

}
