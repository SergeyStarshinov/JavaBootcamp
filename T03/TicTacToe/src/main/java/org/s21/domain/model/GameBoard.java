package org.s21.domain.model;

import org.s21.Config;

import java.util.Arrays;

public class GameBoard implements Cloneable{
  private int[][] gameBoard;

  public GameBoard() {
    gameBoard = new int[Config.BOARD_SIZE][Config.BOARD_SIZE];
  }

  public int getCell(int row, int col) {
    return gameBoard[row][col];
  }

  public void setCell(int row, int col, int val) {
    gameBoard[row][col] = val;
  }

  public void setCell(Coordinate coordinate, int val) {
    gameBoard[coordinate.x()][coordinate.y()] = val;
  }

  @Override
  public String toString() {
    return "{" +
        Arrays.toString(gameBoard[0]) + ", " +
        Arrays.toString(gameBoard[1]) + ", " +
        Arrays.toString(gameBoard[2]) +
        '}';
  }
  @Override
  public GameBoard clone() {
    try {
      GameBoard result = (GameBoard) super.clone();
      result.gameBoard = Arrays.stream(this.gameBoard)
          .map(int[]::clone)
          .toArray(int[][]::new);
      return result;
    }
    catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

}
