package org.s21.domain.service;

import org.s21.datasource.service.DatasourceService;
import org.s21.domain.model.Coordinate;
import org.s21.domain.model.Game;
import org.s21.domain.model.GameBoard;
import org.s21.Config;

import java.util.ArrayList;
import java.util.List;

public class MinMaxGameService implements GameService {

  private final DatasourceService datasourceService;

  public MinMaxGameService(DatasourceService datasourceService) {
    this.datasourceService = datasourceService;
  }

  private record Move(Coordinate coordinate, int score) {}

  private Integer checkLine (int a, int b, int c) {
    return (a != Config.VOID_FIELD && a == b && a == c) ? a : Config.VOID_FIELD;
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
    List<Coordinate> possibleMoves = getPossibleMoves(gameBoard);

    int winner = endGame(gameBoard);
    if (winner == Config.AI_PLAYER) {
      return new Move(null, 10);
    } else if (winner == Config.HUMAN_PLAYER) {
      return new Move(null, -10);
    } else if (winner == Config.DRAW) {
      return new Move(null, 0);
    }

    List<Move> moves = new ArrayList<>();
    for (Coordinate coordinate : possibleMoves) {
      int nextPlayer = (player == Config.AI_PLAYER) ? Config.HUMAN_PLAYER : Config.AI_PLAYER;
      GameBoard nextMoveBoard = gameBoard.clone();
      nextMoveBoard.setCell(coordinate, player);
      Move nextMove = new Move(coordinate, minMax(nextMoveBoard, nextPlayer).score());
      moves.add(nextMove);
    }

    Move bestMove;
    if (player == Config.AI_PLAYER) {
      bestMove = new Move(null, Integer.MIN_VALUE);
      for (Move move : moves) {
        if (move.score() > bestMove.score()) {
          bestMove = move;
        }
      }
    } else {
      bestMove = new Move(null, Integer.MAX_VALUE);
      for (Move move : moves) {
        if (move.score() < bestMove.score()) {
          bestMove = move;
        }
      }
    }

    return bestMove;
  }

  @Override
  public void nextMove(GameBoard gameBoard) {
    Coordinate nextMove = minMax(gameBoard, Config.AI_PLAYER).coordinate();
    gameBoard.setCell(nextMove.x(), nextMove.y(), Config.AI_PLAYER);
  }

  @Override
  public boolean isValid(Game newGame) {
    GameBoard newGameBoard = newGame.getGameBoard();
    GameBoard oldGameBoard = datasourceService.loadGame(newGame.getUuid()).getGameBoard();
    int count = 0;
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      for (int j = 0; j < Config.BOARD_SIZE; ++ j) {
        if (oldGameBoard.getCell(i, j) != Config.VOID_FIELD) {
          if (oldGameBoard.getCell(i, j) != newGameBoard.getCell(i, j)) {
            return false;
          }
        } else {
          if (newGameBoard.getCell(i, j) != Config.VOID_FIELD) {
            count++;
          }
        }
      }
    }
    return count == 1;
  }

  @Override
  public Integer endGame(GameBoard gameBoard) {
    int winner;
    for (int i = 0; i < Config.BOARD_SIZE; ++i) {
      winner = checkLine(gameBoard.getCell(i, 0),  gameBoard.getCell(i, 1), gameBoard.getCell(i, 2));
      if (winner != Config.VOID_FIELD) {
        return winner;
      }
      winner = (checkLine(gameBoard.getCell(0, i),  gameBoard.getCell(1, i), gameBoard.getCell(2, i)));
      if (winner != Config.VOID_FIELD) {
        return winner;
      }
    }
    winner = checkLine(gameBoard.getCell(0, 0),
        gameBoard.getCell(1, 1), gameBoard.getCell(2, 2));
    if (winner != Config.VOID_FIELD) {
      return winner;
    }

    winner =  checkLine(gameBoard.getCell(0, 2),
        gameBoard.getCell(1, 1),
        gameBoard.getCell(2, 0));

    if (winner != Config.VOID_FIELD) {
      return winner;
    }

    return getPossibleMoves(gameBoard).isEmpty()? Config.DRAW : Config.VOID_FIELD;
  }

  @Override
  public Game newGame(int startPlayer) {
    Game result = new Game();
    if (startPlayer == Config.AI_PLAYER) {
      int row = (int) (Math.random() * 3);
      int col = (int) (Math.random() * 3);
      result.setCell(row, col, Config.AI_PLAYER);
    }
    return result;
  }
}
