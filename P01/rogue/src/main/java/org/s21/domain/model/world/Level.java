package org.s21.domain.model.world;

import org.s21.domain.model.Entity;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;

import java.util.*;
import java.util.stream.Collectors;


public class Level {
    private final Map<Coordinate, Cell> cells;
    private final List<Room> rooms;
    private final List<Entity> entities;


    public Level(Map<Coordinate, Cell> cells, List<Room> rooms,
                 List<Entity> entities) {
        this.cells = cells;
        this.rooms = rooms;
        this.entities = entities;
    }

    public Map<Coordinate, Cell> getCells() { return cells; }

    public Cell getCellBy(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    public Cell getCellBy(int y, int x) {
        return cells.get(new Coordinate(x, y));
    }

    public List<Room> getRooms() {
        return rooms;
    }


    public Entity getEntityBy(Coordinate coordinate) {
        Cell cell = cells.get(coordinate);
        return cell != null ? cell.getEntity() : null;
    }



    public Room getRoomBy(Coordinate coordinate) {
        for (Room room : rooms) {
            if (room.isRoomCoordinate(coordinate)) {
                return room;
            }
        }
        return null;
    }

    public void addEntity(Entity entity) {
        Coordinate coordinate = entity.getCoordinate();
        Cell cell = cells.get(coordinate);
        
        if (cell != null) {
            cell.setEntity(entity);
            cell.changeOccupied();
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        Coordinate coordinate = entity.getCoordinate();
        Cell cell = cells.get(coordinate);
        
        if (cell != null) {
            cell.setEntity(null);
            cell.changeOccupied();
            entities.remove(entity);
        }
    }

    public <T> List<T> getEntityOfType(Class<T> type) {
        return entities.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }
}
