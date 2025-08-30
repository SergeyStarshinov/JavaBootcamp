package org.s21.domain.model.items.consumable.elixirs;

import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public abstract class Elixir extends Item implements Consumable {

    private final AttributeType attribute;

    protected final int power;
    protected final int time;

    public Elixir(Coordinate coordinate, AttributeType attribute, int power, int time, String name) {
        super(coordinate, ItemType.ELIXIR, name);
        this.attribute = attribute;
        this.power = power;
        this.time = time;
    }

    public AttributeType getAttribute() {
        return attribute;
    }

    public int getPower() {
        return power;
    }

    public int getTime() {
        return time;
    }
}
