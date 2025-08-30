package org.s21.domain.model.items.consumable.elixirs;

import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public class AgiElixir extends Elixir implements Consumable {

    public AgiElixir(Coordinate coordinate, int power, int time, String name) {
        super(coordinate, AttributeType.AGILITY, power, time, name);
    }
}
