package org.s21.infrastructure.pathfinder;


import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.TerrarianType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {

    public static List<Coordinate> pathFinder(Coordinate start, Coordinate end, Map<Coordinate, Cell> playground) {
        // Очередь для границы
        Queue<Coordinate> frontier = new LinkedList<>();
        frontier.add(start);
        // Map для запоминания путей к промежуточным точкам
        HashMap<Coordinate, Coordinate> cameFrom = new HashMap<>();
        cameFrom.put(start, null);

        while (!frontier.isEmpty()) {
            Coordinate current = frontier.poll();
            if (current == end) {
                break;
            }

            // Формирование списка соседей
            List<Coordinate> neighbors = Coordinate.getNeighbors(current.getX(), current.getY());

            // Проверка соседей, смещение только по 1 координате
            for (Coordinate neighbor : neighbors) {
                Cell currentCell = playground.get(neighbor);
                if (currentCell == null) continue;
                TerrarianType currentTerrarianType = currentCell.getTerrarianType();

                boolean isFloor = currentTerrarianType.equals(TerrarianType.FLOOR);
                boolean isCorridor = currentTerrarianType.equals(TerrarianType.CORRIDOR);
                boolean isBlocked = currentCell.isOccupied() && !neighbor.equals(end);

                boolean isNotDiagonal = (neighbor.getX() - current.getX()) * (neighbor.getY() - current.getY()) == 0;
                boolean isWalkable = ((isFloor || isCorridor) && !isBlocked);

                if (isNotDiagonal && isWalkable && !cameFrom.containsKey(neighbor)) {
                    frontier.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
        // Сборка итогового пути, если путь не построен, то возвращается пустой список
        List<Coordinate> path = new LinkedList<>();
        Coordinate current = end;
        do {
            path.addFirst(current);
            current = cameFrom.get(current);
        } while (current != null);
        // если путь не найден, то удалится финальная точка и список станет пустой
        // если путь построен, то из списка удалится стартовая точка
        path.removeFirst();
        return path;
    }

}
