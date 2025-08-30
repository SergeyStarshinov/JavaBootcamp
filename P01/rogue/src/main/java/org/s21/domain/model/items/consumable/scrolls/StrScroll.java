package org.s21.domain.model.items.consumable.scrolls;

import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public class StrScroll extends Scroll implements Consumable {
    public StrScroll(Coordinate coordinate, int power, String name) {
        super(coordinate, AttributeType.STRENGTH, power, name);
    }
}
