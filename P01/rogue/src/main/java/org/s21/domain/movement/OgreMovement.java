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


public final class OgreMovement extends BaseMovementStrategy {

    @Override
    protected void moveByPattern(Enemy enemy, Level level) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions, Config.RANDOM);

        Coordinate start = enemy.getCoordinate();

        for (Direction dir : directions) {
            int midY = start.getY() + dir.getDeltaY();
            int midX = start.getX() + dir.getDeltaX();
            int newY = start.getY() + dir.getDeltaY() * 2;
            int newX = start.getX() + dir.getDeltaX() * 2;

            Cell midCell = level.getCellBy(midY, midX);
            Cell destCell = level.getCellBy(newY, newX);
            
            boolean midFree = midCell != null && !midCell.isOccupied() &&
                midCell.getTerrarianType() != TerrarianType.WALL;
            boolean destFree = destCell != null && !destCell.isOccupied() &&
                destCell.getTerrarianType() != TerrarianType.WALL;

            if (midFree && destFree) {
                enemy.move(dir, 2);
                return;
            }
        }
    }
}
