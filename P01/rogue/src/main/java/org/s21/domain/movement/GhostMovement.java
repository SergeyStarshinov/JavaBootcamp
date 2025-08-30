package org.s21.domain.movement;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.world.Level;
import org.s21.domain.model.world.Room;

import java.util.ArrayList;
import java.util.List;

public final class GhostMovement extends BaseMovementStrategy {
    @Override
    protected void moveByPattern(Enemy enemy, Level level) {
        List<Coordinate> freeCells = getFreeCoordinatesInRoom(enemy, level);
        if (!freeCells.isEmpty()) {
            Coordinate newCoordinate = freeCells.get(Config.RANDOM.nextInt(freeCells.size()));
            enemy.move(newCoordinate);
        }
    }

    private List<Coordinate> getFreeCoordinatesInRoom(Enemy enemy, Level level) {
        Room room = level.getRoomBy(enemy.getCoordinate());
        List<Coordinate> freeCoordinates = new ArrayList<>();

        int x0 = room.getCoordinate().getX() + 1;
        int y0 = room.getCoordinate().getY() + 1;
        int width = room.getWidth() - 2;
        int height = room.getHeight() - 2;
        for (int y = y0; y < y0 + height; y++) {
            for (int x = x0; x < x0 + width; x++) {
                Coordinate coord = new Coordinate(x, y);
                if (!level.getCellBy(y, x).isOccupied()) {
                    freeCoordinates.add(coord);
                }
            }
        }
        return freeCoordinates;
    }
}
