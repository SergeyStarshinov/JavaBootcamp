package org.s21.domain.model.world;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;

import java.util.Map;

public class Room {

    private final Coordinate coordinate;
    private final int width;
    private final int height;
    private boolean isStartRoom;

    public Room(Coordinate coordinate, int width, int height) {
        this.coordinate = coordinate;
        this.width = width;
        this.height = height;
        this.isStartRoom = false;
    }


    public boolean isRoomCoordinate(Coordinate coordinate) {
        int xMin = this.coordinate.getX();
        int xMax = this.coordinate.getX() + width - 1;
        int yMin = this.coordinate.getY();
        int yMax = this.coordinate.getY() + height - 1;

        int x = coordinate.getX();
        int y = coordinate.getY();
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public boolean isStartRoom() {
        return isStartRoom;
    }

    public void setStartRoom(boolean startRoom) {
        isStartRoom = startRoom;
    }

    public Coordinate getRandomCoordinate() {
        int x = Config.RANDOM.nextInt(coordinate.getX() + 1, coordinate.getX() + width - 1);
        int y = Config.RANDOM.nextInt(coordinate.getY() + 1, coordinate.getY() + height - 1);
        return new Coordinate(x, y);
    }
    public void setVisible(Map<Coordinate, Cell> cells) {
        int roomX = coordinate.getX();
        int roomY = coordinate.getY();
        for (int y = roomY; y < roomY + height; ++y) {
          for (int x = roomX; x < roomX + width; ++x) {
              cells.get(new Coordinate(x, y)).setVisible();
          }
        }
  }
}
