package org.s21.application.usecase.inventory;

import org.s21.domain.model.Backpack;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;

public class PickUpItem {
  public static boolean execute(GameSession gameSession, Coordinate newHeroCoordinate) {
    Item item = (Item) gameSession.getLevel().getEntityBy(newHeroCoordinate);
    Backpack backpack = gameSession.getBackpack();
    if (backpack.isFull(item.getType())) {
      gameSession.appendMessage("The backpack is full");
      return false;
    }
    backpack.addItem(item);
    gameSession.getLevel().removeEntity(item);
    gameSession.appendMessage("You have picked up " + item.getName());
    return true;
  }
}
