package org.s21.domain.model.items.consumable.scrolls;

import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public abstract class Scroll extends Item implements Consumable {

    private final AttributeType attribute;

    protected final int power;

    public Scroll(Coordinate coordinate, AttributeType attribute, int power, String name) {
        super(coordinate, ItemType.SCROLL, name);
        this.attribute = attribute;
        this.power = power;
    }

    public AttributeType getAttribute() {
        return attribute;
    }

    public int getPower() {
        return power;
    }
}
