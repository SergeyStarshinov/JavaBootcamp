package org.s21.domain.model.entity.creature.enemies;

import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.combat.SnakeAttack;
import org.s21.domain.movement.SnakeMovement;

public class Snake extends Enemy {

    public Snake(Coordinate coordinate, EnemyStats stats) {
        super(coordinate, stats, EnemyType.SNAKE, new SnakeMovement(), new SnakeAttack());
    }

    @Override
    public void move(Coordinate coordinate) {
        super.move(coordinate);
    }
}
