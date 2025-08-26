package org.s21.datasource.model;
import java.util.UUID;

public class GameData {
  private final UUID uuid;
  private final GameBoardData gameBoard;

  public GameData(UUID uuid, GameBoardData gameBoard) {
    this.uuid = uuid;
    this.gameBoard = gameBoard;
  }
}
