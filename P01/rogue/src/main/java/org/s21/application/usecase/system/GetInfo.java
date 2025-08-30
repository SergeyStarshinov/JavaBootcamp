package org.s21.application.usecase.system;

import org.s21.domain.model.entity.stats.BaseStats;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;

import java.util.Map;
import java.util.stream.Collectors;

public class GetInfo {

  // Получить всю видимую область карты.
  // Используется после очистки экрана: при старте уровня, после использования рюкзака, печати Help, статистики и т.п
  public static Map<Coordinate, Cell> getVisibleMap(GameSession gameSession) {
    return gameSession.getLevel().getCells().entrySet().stream()
        .filter(e->e.getValue().isVisible() || e.getValue().isVisited())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public static String getHeroStatus(GameSession gameSession) {
    BaseStats heroStats = gameSession.getHero().getStats();
    Item currentWeapon = gameSession.getHero().getCurrentWeapon();
    return "Level:" + gameSession.getCurrentLevel() + " Health:" + heroStats.getHealth() +
        "(" + heroStats.getMaxHealth() + ")" + " Strength:" + heroStats.getStrength() +
        " Agility:" + heroStats.getAgility() + " Gold:" + gameSession.getBackpack().getGold() +
        " Weapon:" + ((currentWeapon == null) ? "No weapon" : currentWeapon.getName());
  }
}
