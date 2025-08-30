package org.s21.domain.combat;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.entity.creature.Creature;
import org.s21.domain.model.entity.stats.BaseStats;

import static org.s21.infrastructure.config.Config.BASE_CHANCE_CRITICAL_DAMAGE;
import static org.s21.infrastructure.config.Config.RANDOM;


final class DamageCalculator {

    public static CombatResult calculateDamage(Creature attacker, Creature defender) {
        BaseStats attackerStats = attacker.getStats();
        BaseStats defenderStats = defender.getStats();

        if (isDodge(attackerStats.getAgility(), defenderStats.getAgility())) {
            return new CombatResult(0, true, false);
        }

        int damage = attacker.getDamage();

        boolean isCritical = isCritical(attackerStats.getAgility());
        if(isCritical) {
            damage *= Config.BASE_CRITICAL_COEFFICIENT;
        }

        return new CombatResult(damage, false, isCritical);
    }

    private static boolean isDodge(int attackerAgi, int defenderAgi) {
        if (attackerAgi >= defenderAgi) return false;
        double chance = 1 - (defenderAgi - attackerAgi) / (double) attackerAgi;
        return RANDOM.nextDouble() < Math.max(0, chance);
    }

    private static boolean isCritical(int attackerAgi) {
        double criticalChance = attackerAgi * BASE_CHANCE_CRITICAL_DAMAGE;
        return RANDOM.nextDouble() < criticalChance;
    }
}
