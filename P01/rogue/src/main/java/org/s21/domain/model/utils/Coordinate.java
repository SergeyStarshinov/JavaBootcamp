package org.s21.domain.model.utils;

import org.s21.infrastructure.config.Config;

import java.util.ArrayList;
import java.util.List;

public final class Coordinate implements Cloneable {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static List<Coordinate> getNeighbors(int x, int y) {
        List<Coordinate> neighbors = new ArrayList<>();
        for (int ny = -1; ny <= 1; ny++) {
            if (y + ny < 0 || y + ny >= Config.MAP_HEIGHT) continue;
            for (int nx = -1; nx <= 1; nx++) {
                if ((nx == 0 && ny == 0) || x + nx < 0 || x + nx >= Config.MAP_WIDTH) continue;
                neighbors.add(new Coordinate(x + nx, y + ny));
            }
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public Coordinate clone() {
        try {
            return (Coordinate) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
