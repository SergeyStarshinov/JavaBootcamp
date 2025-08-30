package org.s21.domain.combat;

import org.s21.domain.model.entity.creature.Creature;

public class BasicAttack implements CombatStrategy {
    @Override
    public String combat(Creature attacker, Creature defender) {
        CombatResult combatResult;
        String result = "";
        if (!attacker.isSleeping()) {
            combatResult = heroAttack(attacker, defender);
            result = "Hero: " + combatResult + " ";
        }
        return result;
    }


    CombatResult heroAttack(Creature hero, Creature enemy) {
        CombatResult heroResult = DamageCalculator.calculateDamage(hero, enemy);
        if (heroResult.isNotDodge()) {
            enemy.takeDamage(heroResult.getDamage());
        }
        return heroResult;
    }

    @Override
    public CombatResult enemyAttack(Creature enemy, Creature hero) {
        CombatResult enemyResult = DamageCalculator.calculateDamage(enemy, hero);

        if (enemyResult.isNotDodge()) {
            hero.takeDamage(enemyResult.getDamage());
        }
        return enemyResult;
    }

}
