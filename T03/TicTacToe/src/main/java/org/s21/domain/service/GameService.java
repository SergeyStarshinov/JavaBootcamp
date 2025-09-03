package org.s21.domain.service;

import org.s21.domain.model.Game;
import org.s21.domain.model.GameBoard;

public interface GameService {

  void nextMove(GameBoard gameBoard);

  boolean isValid(Game newGame);

  Integer endGame(GameBoard gameBoard);

  Game newGame(int startPlayer);
}
