package org.s21.domain.model.items.consumable.food;

import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public abstract class Food extends Item implements Consumable {

    protected final int healPower;

    public Food(Coordinate coordinate, int healPower, String name) {
        super(coordinate, ItemType.FOOD, name);
        this.healPower = healPower;
    }

    public int getHealPower() {
        return healPower;
    }
}
