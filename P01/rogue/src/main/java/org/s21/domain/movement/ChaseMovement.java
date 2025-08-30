package org.s21.domain.movement;

import org.s21.infrastructure.pathfinder.BFS;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.Level;

import java.util.List;

public final class ChaseMovement implements MovementStrategy {

    @Override
    public void move(Enemy enemy, Level level, Coordinate heroPos) {

        Coordinate enemyPos = enemy.getCoordinate();

        int distance = Math.abs(heroPos.getX() - enemyPos.getX()) + Math.abs(heroPos.getY() - enemyPos.getY());

        int hostility = enemy.getStats().getHostility();

        if (distance <= hostility) {
            chaseHero(enemy, level, heroPos);
        }
    }

    private void chaseHero(Enemy enemy, Level level, Coordinate heroPos) {
        Coordinate enemyPos = enemy.getCoordinate();

        List<Coordinate> path = BFS.pathFinder(enemyPos, heroPos, level.getCells());

        if (!path.isEmpty()) {
            enemy.move(path.getFirst());
        }
    }
}
