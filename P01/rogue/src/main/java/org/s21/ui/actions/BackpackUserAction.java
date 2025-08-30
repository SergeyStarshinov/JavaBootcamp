package org.s21.ui.actions;

import org.s21.control.UserInput;
import org.s21.application.usecase.inventory.UseItem;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;
import org.s21.ui.console.PrintGame;

import java.util.HashMap;
import java.util.Map;

public class BackpackUserAction extends UserAction{
  private final ItemType type;

  public BackpackUserAction(ItemType type) {
    this.type = type;
  }

  @Override
  public Map<Coordinate, Cell> doAction(GameSession gameSession) {
    if (type == ItemType.WEAPON) {
      selectWeapon(gameSession);
    } else {
      selectConsumable(gameSession);
    }
    return new HashMap<>();
  }

  private void selectWeapon(GameSession gameSession) {
    Item[] items = gameSession.getBackpack().getItemsByType(type);
    PrintGame.printItemsToUse(items,true, gameSession.getHero().getCurrentWeapon());
    int inputCode = UserInput.getUserInput().getCode() - '0';
    if (inputCode == 0) {
      UseItem.disarm(gameSession);
    } else if (inputCode >= 1 && inputCode <= 9) {
      Item item = items[inputCode - 1];
      items[inputCode - 1]= null;
      if (item != null) {
        gameSession.appendMessage(item.getName() + " was equipped");
        UseItem.changeWeapon(gameSession, item);
      }
    }
  }

  private void selectConsumable(GameSession gameSession) {
    Item[] items = gameSession.getBackpack().getItemsByType(type);
    PrintGame.printItemsToUse(items,false,null);
    int inputCode = UserInput.getUserInput().getCode() - '0';
    if (inputCode >= 1 && inputCode <= 9) {
      Item item =items[inputCode - 1];
      items[inputCode - 1]= null;
      if (item != null) {
        gameSession.appendMessage(item.getName() + " was used");
        UseItem.useConsumable(gameSession, item);
      }
    }
  }
}
