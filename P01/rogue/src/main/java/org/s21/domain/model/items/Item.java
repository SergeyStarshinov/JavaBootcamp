package org.s21.domain.model.items;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.s21.domain.model.Entity;
import org.s21.domain.model.items.weapons.Axe;
import org.s21.domain.model.items.weapons.Sword;
import org.s21.domain.model.items.weapons.Weapon;
import org.s21.domain.model.utils.Coordinate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_kind")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Weapon.class, name = "weapon"),
        @JsonSubTypes.Type(value = Sword.class, name = "sword"),
        @JsonSubTypes.Type(value = Axe.class, name = "axe")
})
public class Item extends Entity {
    protected final ItemType type;
    protected final String name;

    public Item(Coordinate coordinate, ItemType type, String name) {
        super(coordinate);
        this.type = type;
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() { return name; }
}
