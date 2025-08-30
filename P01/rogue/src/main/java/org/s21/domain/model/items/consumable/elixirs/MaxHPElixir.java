package org.s21.domain.model.items.consumable.elixirs;

import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.consumable.Consumable;
import org.s21.domain.model.utils.Coordinate;

public class MaxHPElixir extends Elixir implements Consumable {

    public MaxHPElixir(Coordinate coordinate, int power, int time, String name) {
        super(coordinate, AttributeType.MAX_HEALTH, power, time, name);
    }
}
