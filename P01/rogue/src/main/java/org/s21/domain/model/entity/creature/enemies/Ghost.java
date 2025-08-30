package org.s21.domain.model.entity.creature.enemies;

import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.combat.BasicAttack;
import org.s21.domain.movement.GhostMovement;

public class Ghost extends Enemy {
    public Ghost(Coordinate coordinate, EnemyStats stats) {
        super(coordinate, stats, EnemyType.GHOST, new GhostMovement(), new BasicAttack());
    }
}
