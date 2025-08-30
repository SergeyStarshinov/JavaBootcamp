package org.s21.domain.model.entity.creature.enemies;

import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.combat.OgreAttack;
import org.s21.domain.movement.OgreMovement;

public class Ogre extends Enemy {

    public Ogre(Coordinate coordinate, EnemyStats stats) {
        super(coordinate, stats, EnemyType.OGRE, new OgreMovement(), new OgreAttack());
    }
}
