package org.s21.domain.movement;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.Level;
import org.s21.domain.model.utils.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class BasicMovement extends BaseMovementStrategy {

    @Override
    protected void moveByPattern(Enemy enemy, Level level) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions, Config.RANDOM);

        Coordinate start = enemy.getCoordinate();

        for (Direction dir : directions) {
            int newY = start.getY() + dir.getDeltaY();
            int newX = start.getX() + dir.getDeltaX();
            
            Cell targetCell = level.getCellBy(newY, newX);
            if (targetCell != null && !targetCell.isOccupied() && targetCell.getTerrarianType() != TerrarianType.WALL) {
                enemy.move(dir, 1);
                return;
            }
        }
    }
}
