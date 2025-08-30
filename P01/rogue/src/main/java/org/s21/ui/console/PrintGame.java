package org.s21.ui.console;

import jcurses.system.CharColor;
import jcurses.system.Toolkit;
import org.s21.infrastructure.config.Config;
import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Effect;
import org.s21.domain.model.items.Item;

import java.util.Map;

public class PrintGame {

  public static final CharColor colorBlack = new CharColor(CharColor.BLACK, CharColor.BLACK);
  public static final CharColor colorWhite = new CharColor(CharColor.BLACK, CharColor.WHITE);
  public static final CharColor colorRed = new CharColor(CharColor.BLACK, CharColor.RED);
  public static final CharColor colorGreen = new CharColor(CharColor.BLACK, CharColor.GREEN);
  public static final CharColor colorYellow = new CharColor(CharColor.BLACK, CharColor.YELLOW);

  public static void printFrame(Map<Coordinate, Character> updateMapChar, String heroStatus, Hero hero,
                                String message) {
    printMap(updateMapChar);
    printHeroStatus(heroStatus);
    printEffects(hero);
    printMessage(message);
  }

  public static void printMap (Map<Coordinate, Character> updateMapChar) {
    for (Map.Entry<Coordinate, Character> entry : updateMapChar.entrySet()) {
      char c = entry.getValue();
      Coordinate coordinate = entry.getKey();
      printChar(c, coordinate.getX(), coordinate.getY());
    }
  }

  public static void clear() {
    String stringForClear = ".".repeat(Config.MAP_WIDTH);
    for (int y = 0; y < Config.MAP_HEIGHT + 2; y++) {
        Toolkit.printString(stringForClear, 0, y, colorBlack);
    }
  }

  public static void printHeroStatus(String heroStatus) {
    Toolkit.printString(".".repeat(Config.MAP_WIDTH), 0, Config.MAP_HEIGHT, colorBlack);
    outputLine(heroStatus, 2, Config.MAP_HEIGHT, colorRed);
  }

  public static void printMessage(String message) {
    Toolkit.printString(".".repeat(Config.MAP_WIDTH), 0, Config.MAP_HEIGHT + 2, colorBlack);
    outputLine(message, 2, Config.MAP_HEIGHT + 2, colorWhite);
  }

  public static void printEffects(Hero hero) {
    Toolkit.printString(".".repeat(Config.MAP_WIDTH), 0, Config.MAP_HEIGHT + 1, colorBlack);
    StringBuilder effectsString = new StringBuilder("Effects:");
    for (Effect effect : hero.getEffects()) {
      effectsString.append(" ");
      effectsString.append(switch (effect.getElixir().getAttribute()) {
        case MAX_HEALTH -> "H";
        case AGILITY -> "A";
        case STRENGTH -> "S";
        default -> "";
      });
      effectsString.append("+");
      effectsString.append(effect.getElixir().getPower());
      effectsString.append(":");
      effectsString.append(effect.getTimeLeft());
    }
    outputLine(effectsString.toString(), 2, Config.MAP_HEIGHT + 1, colorRed);
  }

  public static void printItemsToUse(Item[] items, boolean isWeapon, Item currentWeapon) {
    int x = 2, y = 1;
    outputLine("CHOOSE ITEM TO USE:", x + 2, y++, colorWhite);
    if (isWeapon) {
      String remove = "0 - no current weapon, nothing to remove";
      if (currentWeapon != null) remove = "0 - unequip " + currentWeapon.getName();
      outputLine(remove, x, y++, colorWhite);
    }
    for (int i = 0; i < items.length; ++i) {
      String itemName;
      if (items[i] == null) {
        itemName = "Empty slot";
      } else {
        itemName = items[i].getName();
      }
      outputLine((i + 1) + " - "+ itemName, x, y++, colorWhite);
    }
  }

  public static void printHelp() {
    clear();
    int x = 2, y = 0;
    outputLine("HERO CONTROL KEYS:", x + 2, y++, colorRed);
    outputLine("Esc - exit", x, y++, colorWhite);
    outputLine("W - move up", x, y++, colorWhite);
    outputLine("S - move down", x, y++, colorWhite);
    outputLine("A - move left", x, y++, colorWhite);
    outputLine("D - move right", x, y++, colorWhite);
    outputLine("H - change weapon", x, y++, colorWhite);
    outputLine("J - use a food", x, y++, colorWhite);
    outputLine("K - use an elixir", x, y++, colorWhite);
    outputLine("E - use a scroll", x, y++, colorWhite);
    outputLine("I - this help screen", x, y++, colorWhite);
    outputLine("GAME LOGIC:", x + 2, y++, colorRed);
    outputLine("To move to the next level, move to the % mark", x, y++, colorWhite);
    outputLine("You have to pass 21 levels to win the game", x, y++, colorWhite);
    outputLine("To pick up an item, move to the cell with it", x, y++, colorWhite);
    outputLine("To attack a monster, move at enemy direction", x, y++, colorWhite);
    outputLine("MARKS ON THE MAP:", x + 2, y++, colorRed);
    outputLine("@ - Hero", x, y++, colorWhite);
    outputLine("Enemies: z - Zombie, v - Vampire, g - Ghost, O - Ogre, s - Snake Mage", x, y++, colorWhite);
    outputLine("% - Next level passage", x, y++, colorWhite);
    outputLine("Items: F - Food, E - Elixir, W - Weapon, I - Scroll",
        x, y++, colorWhite);
    outputLine("Press any key to continue...", x, y, colorGreen);
  }


  private static void outputLine(String line, int x, int y, CharColor color) {
    String[] substrings = line.split(" ");
    int pos = x;
    for (String substring : substrings) {
      Toolkit.printString(substring, pos, y, color);
      pos += substring.length() + 1;
    }
  }

  private static CharColor defineColor (char c) {
    return switch (c) {
      case Config.ZOMBIE -> colorGreen;
      case Config.VAMPIRE -> colorRed;
      case Config.OGRE -> colorYellow;
      case Config.FOG -> colorBlack;
      default -> colorWhite;
    };
  }

  private static void printChar(char c, int x, int y) {
    Toolkit.printString("" + c, x, y, defineColor(c));
  }

}
