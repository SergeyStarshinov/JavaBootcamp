package org.s21.domain.movement;

import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SnakeMovement extends BaseMovementStrategy {

    private static final int[][] DIAGONALS = {
            {-1, -1}, // UP_LEFT
            {-1, 1},  // UP_RIGHT
            {1, -1},  // DOWN_LEFT
            {1, 1}    // DOWN_RIGHT
    };

    @Override
    protected void moveByPattern(Enemy enemy, Level level) {
        Coordinate start = enemy.getCoordinate();
        List<int[]> diagonals = new ArrayList<>(Arrays.asList(DIAGONALS));

        for (int[] dir : diagonals) {
            int newY = start.getY() + dir[1];
            int newX = start.getX() + dir[0];
            
            Cell targetCell = level.getCellBy(newY, newX);
            if (targetCell != null && !targetCell.isOccupied() && targetCell.getTerrarianType() != TerrarianType.WALL) {
                enemy.setCoordinate(new Coordinate(newX, newY));
                return;
            }
        }
    }
}
