package org.s21.domain.model.items.consumable.scrolls;

import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public class AgiScroll extends Scroll implements Consumable {

    public AgiScroll(Coordinate coordinate, int power, String name) {
        super(coordinate, AttributeType.AGILITY, power, name);
    }
}
