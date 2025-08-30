package org.s21.domain.model.entity.stats;

public class EnemyStats extends BaseStats {
    protected final int hostility;

    public EnemyStats(int agility, int strength, int health, int maxHealth, int hostility) {
        super(agility, strength, health, maxHealth);
        this.hostility = hostility;
    }


    public int getHostility() {
        return hostility;
    }

    public void improveStats(int level) {
        int coefficient = 10 * (level / 5 + 1);
        this.agility *= coefficient;
        this.strength *= coefficient;
        this.health *= coefficient;
        this.maxHealth *= coefficient;
    }
}
