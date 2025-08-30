package org.s21.application.usecase.movement;

import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Direction;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.GameSession;
import org.s21.domain.model.world.Room;

import java.util.HashMap;
import java.util.Map;

public class UpdateVisibility {
    
    public static Map<Coordinate, Cell> updateVisibility(GameSession gameSession, 
                                                        Coordinate oldCoordinate, 
                                                        Coordinate newCoordinate) {
        Map<Coordinate, Cell> updateMap = new HashMap<>();
        
        updateNearbyInvisibility(gameSession, oldCoordinate, newCoordinate, updateMap);
        updateNearbyVisibility(gameSession, newCoordinate, updateMap);

        Room oldRoom = gameSession.getLevel().getRoomBy(oldCoordinate);
        Room newRoom = gameSession.getLevel().getRoomBy(newCoordinate);
        
        handleRoomVisibility(gameSession, oldCoordinate, newCoordinate, oldRoom, newRoom, updateMap);
        
        return updateMap;
    }

    private static void updateNearbyInvisibility(GameSession gameSession,
                                               Coordinate oldCoordinate,
                                               Coordinate heroCoordinate,
                                               Map<Coordinate, Cell> updateMap) {
        for (Direction dir : Direction.values()) {
            int nx = oldCoordinate.getX() + dir.getDeltaX();
            int ny = oldCoordinate.getY() + dir.getDeltaY();
            Coordinate coordinate = new Coordinate(nx, ny);
            Cell cell = gameSession.getLevel().getCellBy(coordinate);
            if (cell != null && !(nx == heroCoordinate.getX() && ny == heroCoordinate.getY()) &&
                cell.getTerrarianType() == TerrarianType.CORRIDOR &&
                gameSession.getLevel().getRoomBy(coordinate) == null) {
                cell.setInvisible();
                updateMap.put(coordinate, cell);
            }
        }
    }

    private static void updateNearbyVisibility(GameSession gameSession, 
                                             Coordinate heroCoordinate, 
                                             Map<Coordinate, Cell> updateMap) {
        for (Direction dir : Direction.values()) {
            int nx = heroCoordinate.getX() + dir.getDeltaX();
            int ny = heroCoordinate.getY() + dir.getDeltaY();
            Coordinate coordinate = new Coordinate(nx, ny);
            Cell cell = gameSession.getLevel().getCellBy(coordinate);
            if (cell != null) {
                cell.setVisible();
                updateMap.put(coordinate, cell);
            }
        }
    }
    
    private static void handleRoomVisibility(GameSession gameSession,
                                           Coordinate oldCoordinate,
                                           Coordinate newCoordinate,
                                           Room oldRoom,
                                           Room newRoom,
                                           Map<Coordinate, Cell> updateMap) {
        Coordinate oldDoor = null;
        
        if (newRoom == null && oldRoom == null) {
            for (Direction dir : Direction.values()) {
                int nx = oldCoordinate.getX() + dir.getDeltaX();
                int ny = oldCoordinate.getY() + dir.getDeltaY();
                Coordinate coordinate = new Coordinate(nx, ny);
                Cell cell = gameSession.getLevel().getCellBy(coordinate);
                if (cell != null) {
                    if (gameSession.getLevel().getRoomBy(coordinate) != null
                        && cell.getTerrarianType() == TerrarianType.CORRIDOR) {
                        oldDoor = coordinate;
                    }
                }
            }
        }
        
        if (oldRoom == null && newRoom != null) {
            setRoomVisible(gameSession, newRoom, updateMap);
        } else if (oldRoom != null && newRoom == null || oldDoor != null) {
            Room room = (oldRoom != null) ? oldRoom : gameSession.getLevel().getRoomBy(oldDoor);
            hideRoom(gameSession, room, updateMap);
        }
        
        handleCorridorVisibility(gameSession, newCoordinate, updateMap);
    }
    
    private static void setRoomVisible(GameSession gameSession, Room room, Map<Coordinate, Cell> updateMap) {
        for (int y = room.getCoordinate().getY(); y < room.getCoordinate().getY() + room.getHeight(); y++) {
            for (int x = room.getCoordinate().getX(); x < room.getCoordinate().getX() + room.getWidth(); x++) {
                Coordinate coordinate = new Coordinate(x, y);
                Cell cell = gameSession.getLevel().getCellBy(coordinate);
                cell.setVisible();
                updateMap.put(coordinate, cell);
            }
        }
    }
    
    private static void hideRoom(GameSession gameSession, Room room, Map<Coordinate, Cell> updateMap) {
        for (int y = room.getCoordinate().getY() + 1; y < room.getCoordinate().getY() + room.getHeight() - 1; y++) {
            for (int x = room.getCoordinate().getX() + 1; x < room.getCoordinate().getX() + room.getWidth() - 1; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                Cell cell = gameSession.getLevel().getCellBy(coordinate);
                cell.setInvisible();
                updateMap.put(coordinate, cell);
            }
        }
    }
    
    private static void handleCorridorVisibility(GameSession gameSession, 
                                               Coordinate heroCoordinate, 
                                               Map<Coordinate, Cell> updateMap) {
        Coordinate door = findNearbyDoor(gameSession, heroCoordinate);
        if (door != null) {
            setPartialRoomVisibility(gameSession, heroCoordinate, door, updateMap);
        }
    }
    
    private static Coordinate findNearbyDoor(GameSession gameSession, Coordinate coordinate) {
        for (Direction dir : Direction.values()) {
            int nx = coordinate.getX() + dir.getDeltaX();
            int ny = coordinate.getY() + dir.getDeltaY();
            Coordinate nearbyCoord = new Coordinate(nx, ny);
            Cell cell = gameSession.getLevel().getCellBy(nearbyCoord);
            if (cell != null && 
                gameSession.getLevel().getRoomBy(nearbyCoord) != null && 
                cell.getTerrarianType() == TerrarianType.CORRIDOR) {
                return nearbyCoord;
            }
        }
        return null;
    }
    
    private static void setPartialRoomVisibility(GameSession gameSession,
                                               Coordinate heroCoordinate,
                                               Coordinate door,
                                               Map<Coordinate, Cell> updateMap) {
        boolean isVertical = (heroCoordinate.getX() == door.getX());
        Room room = gameSession.getLevel().getRoomBy(door);
        int roomX = room.getCoordinate().getX();
        int roomY = room.getCoordinate().getY();
        int roomSizeX = room.getWidth();
        int roomSizeY = room.getHeight();
        
        for (int y = roomY; y < roomY + roomSizeY; ++y) {
            int dy = y - door.getY();
            for (int x = roomX; x < roomX + roomSizeX; ++x) {
                int dx = x - door.getX();
                if ((isVertical && Math.abs(dx) <= Math.abs(dy) / 2) ||
                    (!isVertical && Math.abs(dx) / 2 >= Math.abs(dy))) {
                    Coordinate coordinate = new Coordinate(x, y);
                    Cell cell = gameSession.getLevel().getCellBy(coordinate);
                    cell.setVisible();
                    updateMap.put(coordinate, cell);
                }
            }
        }
    }
} 