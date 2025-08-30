package org.s21.domain.movement;

import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.Level;

public interface MovementStrategy {

    void move(Enemy enemy, Level level, Coordinate heroCoord);
}

