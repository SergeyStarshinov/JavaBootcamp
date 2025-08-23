package org.s21.domain.service;

import org.s21.domain.model.Coordinate;
import org.s21.domain.model.GameBoard;
import org.s21.Config;

import java.util.ArrayList;
import java.util.List;

public class GameService implements IGameService {

  private int checkLine (int a, int b, int c) {
    return (a != Config.VOID_FIELD && a == b && a == c) ? a : 0;
  }

  private List<Coordinate> getPossibleMoves(GameBoard gameBoard) {
    List<Coordinate> possibleMoves = new ArrayList<>();
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++j) {
        if (gameBoard.getCell(i, j) == Config.VOID_FIELD) {
          possibleMoves.add(new Coordinate(i, j));
        }
      }
    }
    return possibleMoves;
  }

  private Move minMax(GameBoard gameBoard, int player) {
//    System.out.println(gameBoard);
    List<Coordinate> possibleMoves = getPossibleMoves(gameBoard);

    int winner = endGame(gameBoard);
    if (winner == Config.AI_PLAYER) {
      return new Move(null, 10);
    } else if (winner == Config.HUMAN_PLAYER) {
      return new Move(null, -10);
    } else if (possibleMoves.isEmpty()) {
      return new Move(null, 0);
    }

    List<Move> moves = new ArrayList<>();
    for (Coordinate coordinate : possibleMoves) {
      int nextPlayer = (player == Config.AI_PLAYER) ? Config.HUMAN_PLAYER : Config.AI_PLAYER;
//      GameBoard nextMoveBoard = gameBoard.clone();
      GameBoard nextMoveBoard = gameBoard.copy();
      nextMoveBoard.setCell(coordinate, player);
      Move nextMove = new Move(coordinate, minMax(nextMoveBoard, nextPlayer).getScore());
      moves.add(nextMove);
    }

//    System.out.println(moves);

    Move bestMove;
    if (player == Config.AI_PLAYER) {
      bestMove = new Move(null, Integer.MIN_VALUE);
      for (Move move : moves) {
        if (move.getScore() > bestMove.getScore()) {
          bestMove = move;
        }
      }
    } else {
      bestMove = new Move(null, Integer.MAX_VALUE);
      for (Move move : moves) {
        if (move.getScore() < bestMove.getScore()) {
          bestMove = move;
        }
      }
    }
    System.out.println(possibleMoves);
    System.out.println(moves);
    System.out.println("best - " + bestMove);
    return bestMove;
  }

  @Override
  public void nextMove(GameBoard gameBoard) {
    Coordinate nextMove = minMax(gameBoard, Config.AI_PLAYER).getCoordinate();
    System.out.println(nextMove);
    gameBoard.setCell(nextMove.getX(), nextMove.getY(), Config.AI_PLAYER);
  }

  @Override
  public boolean isValid(GameBoard newGameBoard, GameBoard oldGameBoard) {
    int count = 0;
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++ j) {
        if (oldGameBoard.getCell(i, j) != 0) {
          if (oldGameBoard.getCell(i, j) != newGameBoard.getCell(i, j)) {
            return false;
          }
        } else {
          if (newGameBoard.getCell(i, j) != 0) {
            count++;
          }
        }
      }
    }
    return count == 1;
  }

  @Override
  public int endGame(GameBoard gameBoard) {
    int winner = 0;
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      winner = checkLine(gameBoard.getCell(i, 0),  gameBoard.getCell(i, 1), gameBoard.getCell(i, 2));
      if (winner != 0) {
        return winner;
      }
      winner = (checkLine(gameBoard.getCell(0, i),  gameBoard.getCell(1, i), gameBoard.getCell(2, i)));
      if (winner != 0) {
        return winner;
      }
    }
    winner = checkLine(gameBoard.getCell(0, 0),
        gameBoard.getCell(1, 1), gameBoard.getCell(2, 2));
    if (winner != 0) {
      return winner;
    }
    return checkLine(gameBoard.getCell(0, 2),
        gameBoard.getCell(1, 1),
        gameBoard.getCell(2, 0));
  }
}
