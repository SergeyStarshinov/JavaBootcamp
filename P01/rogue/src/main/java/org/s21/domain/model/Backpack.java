package org.s21.domain.model;

import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;

import java.util.HashMap;
import java.util.Map;

/*
Когда персонаж наступает на предмет,
он автоматически должен добавляться в рюкзак,
если он неполон (в рюкзаке может храниться максимум 9 предметов каждого типа,
сокровища копятся и хранятся в единственной ячейке).
*/

public class Backpack {

    private static final int MAX_ITEMS_PER_TYPE = 9;

    private int gold;
    private final Map<ItemType, Item []> items;

    public Backpack() {
        this.gold = 0;
        this.items = new HashMap<>();
        for (ItemType itemType : ItemType.values()) {
            items.put(itemType, new Item[MAX_ITEMS_PER_TYPE]);
        }
    }

    public void addItem(Item item) {
        ItemType type = item.getType();
        Item[] itemsByType = items.get(type);
        for (int i = 0; i < MAX_ITEMS_PER_TYPE; ++i) {
            if (itemsByType[i] == null) {
                itemsByType[i] = item;
                break;
            }
        }
    }

    public boolean isFull(ItemType type) {
        Item[] itemsByType = items.get(type);
        for (Item item : itemsByType) {
            if (item == null) {
                return false;
            }
        }
        return true;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getGold() {
        return gold;
    }


    public Item[] getItemsByType(ItemType type) {
        return items.get(type);
    }
}
