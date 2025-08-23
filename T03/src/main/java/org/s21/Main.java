package org.s21;

import org.s21.domain.model.Game;
import org.s21.domain.service.GameService;

public class Main {

  public static void main(String[] args){
//    Game game = new Game();
    Game game1 = new Game();
    GameService gameService = new GameService();

    game1.setCell(0, 0, 2);
    game1.setCell(0, 1, 1);
    game1.setCell(1, 1, 1);
    game1.setCell(2, 2, 1);
    game1.setCell(2, 1, 2);
//    game1.setCell(2, 0, 2);
//    System.out.println(gameService.isValid(game1.getGameBoard(), game.getGameBoard()));
    System.out.println(game1);
    gameService.nextMove(game1.getGameBoard());
    System.out.println(game1);

//    game.setCell(0, 0, 1);
//    System.out.println(game);
//    System.out.println(gameService.endGame(game.getGameBoard()));
//
//    game1.setCell(0, 1, 2);
//    System.out.println(gameService.isValid(game1.getGameBoard(), game.getGameBoard()));
//
//    game1.setCell(1, 1, 1);
//    game1.setCell(2, 2, 1);
//    System.out.println(game1);
//    System.out.println(gameService.endGame(game1.getGameBoard()));
//    System.out.println(gameService.isValid(game1.getGameBoard(), game.getGameBoard()));
  }
}
