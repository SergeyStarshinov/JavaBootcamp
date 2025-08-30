package org.s21.domain.model.items.consumable;

public interface Consumable {
    default boolean isConsumable() {
        return true;
    }
}
