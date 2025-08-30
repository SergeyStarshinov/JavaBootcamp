package org.s21.domain.model.entity.creature.enemies;

import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.combat.BasicAttack;
import org.s21.domain.movement.BasicMovement;

public class Zombie extends Enemy {
    public Zombie(Coordinate coordinate, EnemyStats stats) {
        super(coordinate, stats, EnemyType.ZOMBIE, new BasicMovement(), new BasicAttack());
    }
}
