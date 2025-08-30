package org.s21.domain.model.world;

import org.s21.domain.model.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Corridor {
    private final List<Coordinate> path;

    public Corridor() {
        this.path = new ArrayList<>();
    }

    public List<Coordinate> getPath() {
        return path;
    }


    public void add(Coordinate coordinate) {
        path.addLast(coordinate);
    }
}
