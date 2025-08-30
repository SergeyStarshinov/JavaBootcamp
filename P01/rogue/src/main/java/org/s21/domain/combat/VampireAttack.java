package org.s21.domain.combat;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.entity.creature.Creature;

public final class VampireAttack extends BasicAttack implements CombatStrategy {

    private boolean isFirstAttack = true;

    @Override
    public String combat(Creature attacker, Creature defender) {
        CombatResult combatResult;
        String result = "";
        if (!attacker.isSleeping()) {
            if (!isFirstAttack) {
                combatResult = heroAttack(attacker, defender);
                result = "Hero: " + combatResult + " ";
            } else {
                isFirstAttack = false;
                result = "Hero: miss ";
            }
        }

        return result;
    }

    @Override
    public CombatResult enemyAttack(Creature enemy, Creature hero) {
        CombatResult enemyResult = DamageCalculator.calculateDamage(enemy, hero);
        int damage = enemyResult.getDamage();

        int healthStealChance = Config.RANDOM.nextInt(100);

        if (healthStealChance <= Config.BASE_CHANCE_MODIFIER_ATTACK) {
            hero.getStats().setMaxHealth(hero.getStats().getMaxHealth() - Config.VAMPIRE_STEAL_DAMAGE);
        }

        if (enemyResult.isNotDodge()) {
            hero.takeDamage(damage);
        }
        return enemyResult;
    }
}
