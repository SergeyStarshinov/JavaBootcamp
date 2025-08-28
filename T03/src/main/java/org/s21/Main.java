package org.s21;

import org.s21.datasource.service.DatasourceService;
import org.s21.di.AppConfig;
import org.s21.domain.model.Game;
import org.s21.domain.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
//    Game game = new Game();
        SpringApplication.run(Main.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Game game1 = new Game();
        GameService gameService = new GameService();

        game1.setCell(0, 0, 2);
        game1.setCell(0, 1, 1);
        game1.setCell(1, 1, 1);
//    game1.setCell(2, 2, 1);
//    game1.setCell(2, 1, 2);
//    game1.setCell(2, 0, 2);
//    System.out.println(gameService.isValid(game1.getGameBoard(), game.getGameBoard()));
        System.out.println(game1);
        gameService.nextMove(game1.getGameBoard());
        System.out.println(game1);

        DatasourceService datasourceService = context.getBean(DatasourceService.class);

        datasourceService.saveGame(game1);

        System.out.println(datasourceService.loadgame(game1.getUuid()));

    }
}
