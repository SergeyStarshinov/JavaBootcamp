package org.s21.domain.model.entity.creature;

import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.items.weapons.Weapon;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Effect;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Creature {
    private Weapon currentWeapon;
    private final List<Effect> effects;

    public Hero(Coordinate coordinate, int strength, int agility, int health, int maxHealth) {
        super(coordinate, strength, agility, health, maxHealth);
        this.currentWeapon = null;
        this.effects = new ArrayList<>();
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void removeEffect(Effect effect) {
        effects.remove(effect);
    }

    public List<Effect> decreaseEffectsTime() {
        effects.forEach(Effect::decreaseTime);
        return effects.stream().filter(e -> (e.getTimeLeft() == 0)).toList();
    }

    @Override
    public int getDamage() {
        int weaponDamage = (currentWeapon != null) ? currentWeapon.getDamage() : 0;
        return getStats().getStrength() + weaponDamage;
    }

    public String attack(Enemy enemy) {
        return enemy.getCombatStrategy().combat(this, enemy);
    }
}