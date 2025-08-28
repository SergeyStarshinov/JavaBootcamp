package org.s21.domain.model;

import java.util.UUID;

public class Game {

    private final UUID uuid;
    private final GameBoard gameBoard;

    public Game() {
        uuid = UUID.randomUUID();
        gameBoard = new GameBoard();
    }

    public Game(UUID uuid, GameBoard gameBoard) {
        this.uuid = uuid;
        this.gameBoard = gameBoard;
    }

    public UUID getUuid() {
        return uuid;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setCell(int row, int col, int vaL) {
        gameBoard.setCell(row, col, vaL);
    }

    @Override
    public String toString() {
        return "Game{"
                + "uuid=" + uuid
                + ", gameBoard=" + gameBoard
                + '}';
    }

}
