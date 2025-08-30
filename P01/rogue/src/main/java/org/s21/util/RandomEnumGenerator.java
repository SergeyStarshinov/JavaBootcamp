package org.s21.util;

import static org.s21.infrastructure.config.Config.RANDOM;

public class RandomEnumGenerator<T extends Enum<T>> {
    private final T[] values;

    public RandomEnumGenerator(Class<T> e) {
        values = e.getEnumConstants();
    }

    public T randomEnum() {
        return values[RANDOM.nextInt(values.length)];
    }
}
