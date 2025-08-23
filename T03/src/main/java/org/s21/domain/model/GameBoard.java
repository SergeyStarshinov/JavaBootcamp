package org.s21.domain.model;

import org.s21.Config;

import java.util.Arrays;

public class GameBoard {

  private final int[][] gameBoard;

  public GameBoard() {
    gameBoard = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
  }

  public int getCell(int row, int col) {
    return gameBoard[row][col];
  }

  public void setCell(int row, int col, int val) {
    gameBoard[row][col] = val;
  }

  public void setCell(Coordinate coordinate, int val) {
    gameBoard[coordinate.getX()][coordinate.getY()] = val;
  }

  @Override
  public String toString() {
    return "{" +
        Arrays.toString(gameBoard[0]) + ", " +
        Arrays.toString(gameBoard[1]) + ", " +
        Arrays.toString(gameBoard[2]) +
        '}';
  }

//  @Override
//  public GameBoard clone() {
//    try {
//      return (GameBoard) super.clone();
//    } catch (CloneNotSupportedException e) {
//      throw new AssertionError();
//    }
//  }

  public GameBoard copy() {
    GameBoard copyBoard = new GameBoard();
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++ j) {
        copyBoard.setCell(i, j, gameBoard[i][j]);
      }
    }
    return copyBoard;
  }
}
