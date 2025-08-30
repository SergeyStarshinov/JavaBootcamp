package org.s21.domain.model.entity.creature.enemies;

import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.combat.VampireAttack;
import org.s21.domain.movement.BasicMovement;

public class Vampire extends Enemy {

    public Vampire(Coordinate coordinate, EnemyStats stats) {
        super(coordinate, stats, EnemyType.VAMPIRE, new BasicMovement(), new VampireAttack());
    }

}
