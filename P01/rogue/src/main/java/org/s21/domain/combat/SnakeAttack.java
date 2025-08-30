package org.s21.domain.combat;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.entity.creature.Creature;

public final class SnakeAttack extends BasicAttack implements CombatStrategy {

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


    @Override
    public CombatResult enemyAttack(Creature enemy, Creature hero) {
        CombatResult enemyResult = DamageCalculator.calculateDamage(enemy, hero);
        int damage = enemyResult.getDamage();

        if (enemyResult.isNotDodge()) {
            hero.takeDamage(damage);

            int sedationChance = Config.RANDOM.nextInt(100);
            if (sedationChance <= Config.BASE_CHANCE_MODIFIER_ATTACK) {
                hero.setSleeping(true);
            }
        }
        return enemyResult;
    }
}
