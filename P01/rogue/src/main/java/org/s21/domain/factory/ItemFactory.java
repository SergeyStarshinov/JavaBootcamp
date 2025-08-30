package org.s21.domain.factory;

import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.utils.Coordinate;

import org.s21.domain.model.items.consumable.food.Apple;
import org.s21.domain.model.items.consumable.elixirs.AgiElixir;
import org.s21.domain.model.items.consumable.elixirs.MaxHPElixir;
import org.s21.domain.model.items.consumable.elixirs.StrElixir;
import org.s21.domain.model.items.consumable.scrolls.AgiScroll;
import org.s21.domain.model.items.consumable.scrolls.MaxHPScroll;
import org.s21.domain.model.items.consumable.scrolls.StrScroll;
import org.s21.domain.model.items.weapons.Sword;
import org.s21.domain.model.items.weapons.Axe;

import static org.s21.infrastructure.config.Config.DEFAULT_DAMAGE;
import static org.s21.infrastructure.config.Config.DEFAULT_TIME;
import static org.s21.infrastructure.config.Config.MAX_DAMAGE;
import static org.s21.infrastructure.config.Config.MAX_POWER;
import static org.s21.infrastructure.config.Config.MAX_TIME;
import static org.s21.infrastructure.config.Config.MIN_DAMAGE;
import static org.s21.infrastructure.config.Config.MIN_POWER;
import static org.s21.infrastructure.config.Config.MIN_TIME;
import static org.s21.infrastructure.config.Config.RANDOM;

public class ItemFactory {

    public static Item createItem(ItemType itemType, Coordinate coordinate, int level) {
        int healPower = RANDOM.nextInt(MIN_POWER, MAX_POWER) - level;
        int power = RANDOM.nextInt(MIN_POWER, MAX_POWER) - level;
        int time = RANDOM.nextInt(MIN_TIME, MAX_TIME) - level / 5;

        time = Math.max(time, DEFAULT_TIME);

        int damage = RANDOM.nextInt(MIN_DAMAGE, MAX_DAMAGE) + level / 5;

        damage = Math.max(damage, DEFAULT_DAMAGE);

        return switch (itemType) {
            case FOOD -> new Apple(coordinate, healPower, "Apple +" + healPower);
            case ELIXIR -> switch (RANDOM.nextInt(3)) {
                case 0 -> new AgiElixir(coordinate, power, time, "Elixir of Agility +" + power);
                case 1 -> new StrElixir(coordinate, power, time, "Elixir of Strength +" + power);
                default -> new MaxHPElixir(coordinate, power, time, "Elixir of MaxHealth +" + power);
            };
            case SCROLL -> switch (RANDOM.nextInt(3)) {
                case 0 -> new AgiScroll(coordinate, power / 3, "Scroll of Agility +" + (power / 3));
                case 1 -> new StrScroll(coordinate, power / 3, "Scroll of Strength +" + (power / 3));
                default -> new MaxHPScroll(coordinate, power / 3, "Scroll of MaxHealth +" + (power / 3));
            };
            case WEAPON -> RANDOM.nextBoolean() ? new Sword(coordinate, damage, "Sword +" + damage) :
                    new Axe(coordinate, damage, "Axe +" + damage);
        };
    }
}
