package org.s21.application.usecase.movement;

import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;
import org.s21.domain.model.world.Level;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveEnemies {
    public static Map<Coordinate, Cell> execute(GameSession gameSession) {
        Level level = gameSession.getLevel();
        Hero hero = gameSession.getHero();
        List<Enemy> enemies = level.getEntityOfType(Enemy.class);
        Map<Coordinate, Cell> enemiesPositionUpdates = new HashMap<>();
        for (Enemy enemy : enemies) {

            Coordinate oldCoordinate = enemy.getCoordinate().clone();
            level.removeEntity(enemy);
            enemiesPositionUpdates.put(oldCoordinate, level.getCellBy(oldCoordinate));
            Coordinate heroCoordinate = hero.getCoordinate().clone();
            enemy.move(level, heroCoordinate);
            if (enemy.getCoordinate() == heroCoordinate) {
                String message = enemy.attack(hero);
                gameSession.appendMessage(message);
                enemy.move(level, oldCoordinate);
            }
            level.addEntity(enemy);
            Coordinate newCoordinate = enemy.getCoordinate();
            enemiesPositionUpdates.put(newCoordinate, level.getCellBy(newCoordinate));

        }
        return enemiesPositionUpdates;
    }
}
