package org.s21.domain.model.utils;

import org.s21.domain.model.Entity;

public class Cell {
//    private final Coordinate coordinate;
    private TerrarianType terrarianType;
    private Entity entity;
    private boolean isVisited;
    private boolean isOccupied;
    private boolean isVisible;

//    public Cell(Coordinate coordinate, TerrarianType terrarianType, Entity entity) {
//        this.coordinate = coordinate;
    public Cell(TerrarianType terrarianType, Entity entity) {
        this.terrarianType = terrarianType;
        this.entity = entity;
        this.isVisited = false;
        this.isOccupied = false;
        this.isVisible = false;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }


    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        if (terrarianType == TerrarianType.CORRIDOR) {
            isVisited = true;
        }
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public TerrarianType getTerrarianType() {
        return terrarianType;
    }

    public void changeOccupied() {
        this.isOccupied = !isOccupied;
    }


    public void setVisible() {
        this.isVisible = true;
        setVisited();
    }

    public void setInvisible() {
        this.isVisible = false;
    }

    public void setTerrarianType(TerrarianType terrarianType) {
        this.terrarianType = terrarianType;
    }
}
