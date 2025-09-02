package org.s21.web.model;

import java.util.UUID;

public class GameWeb {
  private final UUID uuid;
  private final GameBoardWeb gameBoard;
  private String statusMessage;

  public GameWeb(UUID uuid, GameBoardWeb gameBoard) {
    this.uuid = uuid;
    this.gameBoard = gameBoard;
    this.statusMessage = "";
  }

  public UUID getUuid() {
    return uuid;
  }

  public GameBoardWeb getGameBoard() {
    return gameBoard;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  @Override
  public String toString() {
    return "GameWeb{" +
        "uuid=" + uuid +
        ", gameBoard=" + gameBoard +
        ", statusMessage='" + statusMessage + '\'' +
        '}';
  }
}
