package org.s21.domain.model.entity.creature;

import org.s21.domain.model.Entity;
import org.s21.domain.model.entity.stats.BaseStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Direction;

public abstract class Creature extends Entity {
    private final BaseStats stats;
    private boolean sleeping = false;

    public Creature(Coordinate coordinate, BaseStats stats) {
        super(coordinate);
        this.stats = stats;
    }

    public Creature(Coordinate coordinate, int agility, int strength, int health, int maxHealth) {
        super(coordinate);
        this.stats = new BaseStats(strength, agility, health, maxHealth);
    }

    public BaseStats getStats() {
        return stats;
    }


    public int getDamage() {
        return stats.getStrength();
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }


    public void takeDamage(int damage) {
        int newHealth = Math.max(0, stats.getHealth() - damage);
        stats.setHealth(newHealth);
    }


    public boolean isAlive() {
        return stats.getHealth() > 0;
    }

    public void move(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void move(Direction direction, int step) {
        switch (direction) {
            case UP:
                coordinate.setY(coordinate.getY() - step);
                break;
            case DOWN:
                coordinate.setY(coordinate.getY() + step);
                break;
            case LEFT:
                coordinate.setX(coordinate.getX() - step);
                break;
            case RIGHT:
                coordinate.setX(coordinate.getX() + step);
                break;
        }
    }

}
