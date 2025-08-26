package org.s21.datasource.model;

public class GameBoardData {
  private final int[][] gameBoard;

  public GameBoardData() {
    gameBoard = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
  }

  public int getCell(int row, int col) {
    return gameBoard[row][col];
  }
  public void setCell(int row, int col, int val) {
    gameBoard[row][col] = val;
  }

}
