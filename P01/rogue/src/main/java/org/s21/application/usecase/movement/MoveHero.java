package org.s21.application.usecase.movement;

import org.s21.application.usecase.combat.Combat;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Direction;
import org.s21.domain.model.world.GameSession;
import org.s21.application.usecase.inventory.PickUpItem;

import java.util.HashMap;
import java.util.Map;

public class MoveHero {

    public static Map<Coordinate, Cell> moveHero(GameSession gameSession, Direction direction) {
        Map<Coordinate, Cell> updateMap = new HashMap<>();
        Coordinate oldHeroCoordinate = gameSession.getHero().getCoordinate();
        Cell oldPlace = gameSession.getLevel().getCellBy(oldHeroCoordinate);

        int newX = oldHeroCoordinate.getX() + direction.getDeltaX();
        int newY = oldHeroCoordinate.getY() + direction.getDeltaY();
        Coordinate newHeroCoordinate = new Coordinate(newX, newY);

        if (!ValidateHeroMovement.canMove(gameSession, newHeroCoordinate)) {
            updateMap.put(oldHeroCoordinate, oldPlace);
            return updateMap;
        }

        if (ValidateHeroMovement.isItem(gameSession, newHeroCoordinate)) {
            if (!PickUpItem.execute(gameSession, newHeroCoordinate)) {
                updateMap.put(oldHeroCoordinate, oldPlace);
                return updateMap;
            }
        }

        if (ValidateHeroMovement.isExit(gameSession, newHeroCoordinate)) {
            HandleLevelTransition.goToNewLevel(gameSession);
            return updateMap;
        }

        if (ValidateHeroMovement.isEnemy(gameSession, newHeroCoordinate)) {
            Enemy enemy = (Enemy) gameSession.getLevel().getEntityBy(newHeroCoordinate);
            String message = Combat.execute(enemy, gameSession.getHero());
            gameSession.appendMessage(message);
            if (!enemy.isAlive()) {
                int gold = enemy.getGold();
                gameSession.getBackpack().addGold(gold);
                gameSession.appendMessage(" You've gained " + gold + " gold");
                gameSession.getLevel().removeEntity(enemy);
                updateMap.put(enemy.getCoordinate(), gameSession.getLevel().getCellBy(enemy.getCoordinate()));
            }
        } else {
            Map<Coordinate, Cell> positionUpdates =
                    UpdateHeroPosition.updatePosition(gameSession, direction);
            updateMap.putAll(positionUpdates);

            Map<Coordinate, Cell> visibilityUpdates =
                    UpdateVisibility.updateVisibility(gameSession, oldHeroCoordinate, newHeroCoordinate);
            updateMap.putAll(visibilityUpdates);
        }
        Map<Coordinate, Cell> enemiesPositonUpdates =
                MoveEnemies.execute(gameSession);
        updateMap.putAll(enemiesPositonUpdates);

        if (gameSession.getHero().isSleeping()) {
            enemiesPositonUpdates = MoveEnemies.execute(gameSession);
            updateMap.putAll(enemiesPositonUpdates);
            gameSession.getHero().setSleeping(false);

        }
        return updateMap;
    }
}
