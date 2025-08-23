package org.s21.domain.service;

import org.s21.domain.model.GameBoard;

public interface IGameService {

  void nextMove(GameBoard gameBoard);

  boolean isValid(GameBoard newGameBoard, GameBoard oldGameBoard);

  int endGame(GameBoard gameBoard);

}
