package org.s21.domain.combat;

import org.s21.domain.model.entity.creature.Creature;

public final class OgreAttack extends BasicAttack implements CombatStrategy {

    private boolean isResting = false;
    private boolean isCounterAttacking = false;

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

        if (isResting) {
            isResting = false;
            isCounterAttacking = true;
            return new CombatResult(0, false, false);
        }

        if (isCounterAttacking) {
            enemyResult.setDodge(false);
        }

        if (enemyResult.isNotDodge()) {
            int damage = enemyResult.getDamage();
            hero.takeDamage(damage);
        }
        isResting = true;

        return enemyResult;
    }
}
