package org.s21.domain.model.items.weapons;

import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.utils.Coordinate;

public abstract class Weapon extends Item {
    protected final int damage;

    public Weapon(Coordinate coordinate, int damage, String name) {
        super(coordinate, ItemType.WEAPON, name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
