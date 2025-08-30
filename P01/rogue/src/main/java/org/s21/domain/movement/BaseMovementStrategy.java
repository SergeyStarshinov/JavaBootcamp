package org.s21.domain.movement;

import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.Level;

public abstract class BaseMovementStrategy implements MovementStrategy {
    
    private final ChaseMovement chaseMovement = new ChaseMovement();

    @Override
    public void move(Enemy enemy, Level level, Coordinate heroCoord) {
        if (shouldChase(enemy, heroCoord)) {
            chaseMovement.move(enemy, level, heroCoord);
        } else {
            moveByPattern(enemy, level);
        }
    }

    private boolean shouldChase(Enemy enemy, Coordinate heroCoord) {
        Coordinate enemyCoord = enemy.getCoordinate();

        int distance = Math.abs(heroCoord.getX() - enemyCoord.getX()) + Math.abs(heroCoord.getY() - enemyCoord.getY());
        int hostility = enemy.getStats().getHostility();

        return distance <= hostility;
    }

    protected abstract void moveByPattern(Enemy enemy, Level level);
} 