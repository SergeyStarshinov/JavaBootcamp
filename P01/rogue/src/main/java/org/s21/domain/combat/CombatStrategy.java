package org.s21.domain.combat;

import org.s21.domain.model.entity.creature.Creature;


public interface CombatStrategy {
    String combat(Creature attacker, Creature defender);

    CombatResult enemyAttack(Creature enemy, Creature hero);
}
