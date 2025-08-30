package org.s21.application.usecase.inventory;

import org.s21.domain.model.Backpack;
import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.items.consumable.elixirs.Elixir;
import org.s21.domain.model.items.consumable.food.Food;
import org.s21.domain.model.items.consumable.scrolls.Scroll;
import org.s21.domain.model.items.weapons.Weapon;
import org.s21.domain.model.utils.Effect;
import org.s21.domain.model.world.GameSession;

public class UseItem {
  public static void useConsumable(GameSession gameSession, Item item) {
    Hero hero = gameSession.getHero();
    if (item.getType() == ItemType.SCROLL) {
      Scroll scroll = (Scroll) item;
      switch (scroll.getAttribute()) {
        case AttributeType.AGILITY ->
            hero.getStats().setAgility(hero.getStats().getAgility() + scroll.getPower());
        case AttributeType.MAX_HEALTH ->
            {
              hero.getStats().setMaxHealth(hero.getStats().getMaxHealth() + scroll.getPower());
              hero.getStats().setHealth(hero.getStats().getHealth() + scroll.getPower());
            }
        case AttributeType.STRENGTH ->
            hero.getStats().setStrength(hero.getStats().getStrength() + scroll.getPower());
      }
    } else if (item.getType() == ItemType.FOOD) {
      int health = hero.getStats().getHealth();
      int maxHealth = hero.getStats().getMaxHealth();
      Food food = (Food) item;
      health += food.getHealPower();
      if (health > maxHealth) {
        health = maxHealth;
      }
      hero.getStats().setHealth(health);
    } else if (item.getType() == ItemType.ELIXIR) {
      Elixir elixir = (Elixir) item;
      switch (elixir.getAttribute()) {
        case AttributeType.AGILITY ->
            hero.getStats().setAgility(hero.getStats().getAgility() + elixir.getPower());
        case AttributeType.MAX_HEALTH ->
        {
          hero.getStats().setMaxHealth(hero.getStats().getMaxHealth() + elixir.getPower());
          hero.getStats().setHealth(hero.getStats().getHealth() + elixir.getPower());
        }
        case AttributeType.STRENGTH ->
            hero.getStats().setStrength(hero.getStats().getStrength() + elixir.getPower());
      }
      hero.addEffect(new Effect(elixir, elixir.getTime()));
    }
  }

  public static void disarm(GameSession gameSession) {
    Backpack backpack = gameSession.getBackpack();
    if (!backpack.isFull(ItemType.WEAPON)) {
      Item item = gameSession.getHero().getCurrentWeapon();
      backpack.addItem(item);
      gameSession.getHero().setCurrentWeapon(null);
      gameSession.appendMessage(item.getName() + " was put into the backpack");
    } else {
      gameSession.appendMessage("The backpack is full");
    }
  }

  public static void changeWeapon(GameSession gameSession, Item item) {
    if (gameSession.getHero().getCurrentWeapon() != null) {
      DropItem.execute(gameSession, gameSession.getHero().getCurrentWeapon());
    }
    gameSession.getHero().setCurrentWeapon((Weapon) item);
  }
}
