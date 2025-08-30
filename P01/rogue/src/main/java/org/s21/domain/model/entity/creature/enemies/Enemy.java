package org.s21.domain.model.entity.creature.enemies;

import org.s21.domain.model.entity.creature.Creature;
import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.Level;
import org.s21.domain.combat.CombatStrategy;
import org.s21.domain.movement.MovementStrategy;

public abstract class Enemy extends Creature {
    private final EnemyType type;

    private final MovementStrategy movementStrategy;
    private final CombatStrategy combatStrategy;

    public Enemy(Coordinate coordinate, EnemyStats stats, EnemyType enemyType,
                 MovementStrategy movementStrategy,
                 CombatStrategy combatStrategy) {
        super(coordinate, stats);
        this.type = enemyType;
        this.movementStrategy = movementStrategy;
        this.combatStrategy = combatStrategy;
    }

    @Override
    public EnemyStats getStats() {
        return (EnemyStats) super.getStats();
    }

    public void move(Level level, Coordinate target) {
        movementStrategy.move(this, level, target);
    }

    public String attack(Hero hero) {
        return "Enemy: " + combatStrategy.enemyAttack(this, hero) + " ";
    }

    public int getGold() {
        return (this.getStats().getStrength() + this.getStats().getAgility() + this.getStats().getMaxHealth()) / 10 + 1;
    }

    public CombatStrategy getCombatStrategy() {
        return combatStrategy;
    }
}


