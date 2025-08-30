package org.s21.infrastructure.generation;


import org.s21.infrastructure.config.Config;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.Corridor;
import org.s21.domain.model.world.GameSession;
import org.s21.domain.model.world.Level;
import org.s21.domain.model.world.Room;

import java.util.*;


public class LevelGenerator {
    public static Level generateLevel(GameSession gameSession) {
        Map<Coordinate, Cell> cells = new HashMap<>();

        List<Room> rooms = createRooms();

        for (Room room : rooms) {
            for (int y = room.getCoordinate().getY(); y < room.getCoordinate().getY() + room.getHeight(); y++) {
                for (int x = room.getCoordinate().getX(); x < room.getCoordinate().getX() + room.getWidth(); x++) {
                    boolean isWall = (x == room.getCoordinate().getX() || x == room.getCoordinate().getX() + room.getWidth() - 1 ||
                            y == room.getCoordinate().getY() || y == room.getCoordinate().getY() + room.getHeight() - 1);
                    TerrarianType tt = isWall ? TerrarianType.WALL : TerrarianType.FLOOR;
                    cells.put(new Coordinate(x, y), new Cell(tt, null));
                }
            }
        }

        List<Edge> edges = createEdges(rooms);
        List<Corridor> corridors = createCorridors(rooms, edges);

        for (Corridor corridor : corridors) {
            for (Coordinate coordinate : corridor.getPath()) {
                int x = coordinate.getX();
                int y = coordinate.getY();

                cells.put(new Coordinate(x, y), new Cell(TerrarianType.CORRIDOR, null));
            }
        }

        Room startRoom = rooms.get(Config.RANDOM.nextInt(9));
        Coordinate start = startRoom.getRandomCoordinate();
        startRoom.setStartRoom(true);
        startRoom.setVisible(cells);

        gameSession.getHero().setCoordinate(start);
        cells.get(start).changeOccupied();
        cells.get(start).setEntity(gameSession.getHero());

        Room room;
        Coordinate end;
        do {
            room = rooms.get(Config.RANDOM.nextInt(9));
            end = room.getRandomCoordinate();
        } while (room.isStartRoom());
        cells.get(end).setTerrarianType(TerrarianType.EXIT);

        return new Level(cells, rooms, new ArrayList<>());
    }


    private static List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();
        for (int y = 0; y < Config.MAP_HEIGHT; y += Config.GRID_HEIGHT) {
            for (int x = 0; x < Config.MAP_WIDTH; x += Config.GRID_WIDTH) {
                int maxWidth = Math.min(Config.GRID_WIDTH - 2, Config.ROOM_MAX_SIZE);
                int width = Config.RANDOM.nextInt(Config.ROOM_MIN_SIZE, maxWidth);

                int maxHeight = Math.min(Config.GRID_HEIGHT - 2, Config.ROOM_MAX_SIZE);
                int height = Config.RANDOM.nextInt(Config.ROOM_MIN_SIZE, maxHeight);

                int coordinateX = x + Config.RANDOM.nextInt(1, Config.GRID_WIDTH - width - 1);
                int coordinateY = y + Config.RANDOM.nextInt(1, Config.GRID_HEIGHT - height);

                rooms.add(new Room(new Coordinate(coordinateX, coordinateY), width, height));
            }
        }
        return rooms;
    }


    private static class Edge {
        public final Room room1;
        public final Room room2;

        public Edge(Room room1, Room room2) {
            this.room1 = room1;
            this.room2 = room2;
        }
    }

    private static List<Edge> createEdges(List<Room> rooms) {
        List<Edge> edges = new ArrayList<>();
        // Генерация горизонтальных ребер между комнатами
        for (int i = 0; i < Config.ROOMS_IN_HEIGHT; i++) {
            for (int j = 0; j + 1 < Config.ROOMS_IN_WIDTH; j++) {
                int currentRoom = i * Config.ROOMS_IN_HEIGHT + j;
                edges.addLast(new Edge(rooms.get(currentRoom), rooms.get(currentRoom + 1)));
            }
        }
        // Генерация вертикальных ребер между комнатами
        for (int i = 0; i + 1 < Config.ROOMS_IN_HEIGHT; i++) {
            for (int j = 0; j < Config.ROOMS_IN_WIDTH; j++) {
                int currentRoom = i * Config.ROOMS_IN_HEIGHT + j;
                edges.addLast(new Edge(rooms.get(currentRoom), rooms.get(currentRoom + Config.ROOMS_IN_WIDTH)));
            }
        }
        return edges;
    }

    private static List<Corridor> createCorridors(List<Room> rooms, List<Edge> edges) {
        List<Corridor> corridors = new ArrayList<>();
        List<Edge> possibleLinks = new ArrayList<>();
        Set<Room> linkedRooms = new HashSet<>();
        linkedRooms.add(rooms.get(1));
        while (!edges.isEmpty()) {
            // Формирование списка возможных новых коридоров из множества связных комнат
            possibleLinks.clear();
            for (Edge next : edges) {
                if ((linkedRooms.contains(next.room1) && !linkedRooms.contains(next.room2))
                        || (linkedRooms.contains(next.room2) && !linkedRooms.contains(next.room1))) {
                    possibleLinks.addLast(next);
                }
            }
            // Случайный выбор одного ребра, где будет построен новый коридор
            int randomEdgeNumber = Config.RANDOM.nextInt(possibleLinks.size() - 1);
            Edge newLink = possibleLinks.get(randomEdgeNumber);
            possibleLinks.remove(randomEdgeNumber);
            edges.remove(newLink);
            // Генерация коридора
            corridors.addLast(createCorridor(newLink.room1, newLink.room2, rooms));
            // Проверка, есть ли из привязываемой комнаты еще ребра к linkedRooms и,
            // если есть, то строим коридор с некоторой вероятностью
            Room addedRoom = linkedRooms.contains(newLink.room1) ? newLink.room2 : newLink.room1;
            for (Edge edge : possibleLinks) {
                if (edge.room1 == addedRoom || edge.room2 == addedRoom) {
                    // генерация корридора с вероятностью
                    double rnd = Config.RANDOM.nextDouble(0, 1.0);
                    if (rnd > 0.5) {
                        corridors.addLast(createCorridor(edge.room1, edge.room2, rooms));
                    }
                    edges.remove(edge);
                }
            }
            // Добавляем комнату в linkedRooms
            linkedRooms.add(addedRoom);
        }
        return corridors;
    }

    private static Corridor createCorridor(Room room1, Room room2, List<Room> rooms) {
        if (rooms.indexOf(room1) == rooms.indexOf(room2) - 1) {
            return createHorizontalCorridor(room1, room2);
        } else {
            return createVerticalCorridor(room1, room2);
        }
    }

    private static Corridor createHorizontalCorridor(Room room1, Room room2) {
        // У первой комнаты берем случайную координату и сдвигаем на правую стену
        Coordinate corridorBegin = room1.getRandomCoordinate();
        corridorBegin.setX(room1.getCoordinate().getX() + room1.getWidth() - 1);
        // У второй комнаты берем случайную координату и сдвигаем на левую стену
        Coordinate corridorEnd = room2.getRandomCoordinate();
        corridorEnd.setX(room2.getCoordinate().getX());

        Corridor newCorridor = new Corridor();
        // Если Y координаты равны, то строится прямой коридор, иначе - с изгибом
        if (corridorBegin.getY() == corridorEnd.getY()) {
            connectCoordinates(corridorBegin, corridorEnd, newCorridor);
        } else {
            int bend = (corridorBegin.getX() + 1 == corridorEnd.getX() - 1) ? corridorBegin.getX() + 1 :
                    Config.RANDOM.nextInt(corridorBegin.getX() + 1, corridorEnd.getX() - 1);
            Coordinate startBend = new Coordinate(bend, corridorBegin.getY());
            Coordinate endBend = new Coordinate(bend, corridorEnd.getY());
            connectCoordinates(corridorBegin, startBend, newCorridor);
            connectCoordinates(startBend, endBend, newCorridor);
            connectCoordinates(endBend, corridorEnd, newCorridor);
        }
        return newCorridor;
    }

    private static Corridor createVerticalCorridor(Room room1, Room room2) {
        // У первой комнаты берем случайную координату и сдвигаем на нижнюю стену
        Coordinate corridorBegin = room1.getRandomCoordinate();
        corridorBegin.setY(room1.getCoordinate().getY() + room1.getHeight() - 1);
        // У второй комнаты берем случайную координату и сдвигаем на верхнюю стену
        Coordinate corridorEnd = room2.getRandomCoordinate();
        corridorEnd.setY(room2.getCoordinate().getY());

        Corridor newCorridor = new Corridor();
        // Если X координаты равны, то строится прямой коридор, иначе - с изгибом
        if (corridorBegin.getX() == corridorEnd.getX()) {
            connectCoordinates(corridorBegin, corridorEnd, newCorridor);
        } else {
            int bend = (corridorBegin.getY() + 1 == corridorEnd.getY() - 1) ? corridorBegin.getY() + 1 :
                    Config.RANDOM.nextInt(corridorBegin.getY() + 1, corridorEnd.getY() - 1);
            Coordinate startBend = new Coordinate(corridorBegin.getX(), bend);
            Coordinate endBend = new Coordinate(corridorEnd.getX(), bend);
            connectCoordinates(corridorBegin, startBend, newCorridor);
            connectCoordinates(startBend, endBend, newCorridor);
            connectCoordinates(endBend, corridorEnd, newCorridor);
        }
        return newCorridor;
    }

    private static void connectCoordinates(Coordinate c1, Coordinate c2, Corridor corridor) {
        if (c1.getX() == c2.getX()) {
            for (int y = Math.min(c1.getY(), c2.getY()); y <= Math.max(c1.getY(), c2.getY()); y++) {
                corridor.add(new Coordinate(c1.getX(), y));
            }
        } else {
            for (int x = Math.min(c1.getX(), c2.getX()); x <= Math.max(c1.getX(), c2.getX()); x++) {
                corridor.add(new Coordinate(x, c1.getY()));
            }
        }
    }
}