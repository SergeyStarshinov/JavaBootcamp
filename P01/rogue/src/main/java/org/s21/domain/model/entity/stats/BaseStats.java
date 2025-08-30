package org.s21.domain.model.entity.stats;


public class BaseStats {
    protected int agility;
    protected int strength;
    protected int health;
    protected int maxHealth;


    public BaseStats(int strength, int agility, int health, int maxHealth) {
        this.agility = agility;
        this.strength = strength;
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
