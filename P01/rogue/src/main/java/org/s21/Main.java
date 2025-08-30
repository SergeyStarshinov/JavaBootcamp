package org.s21;

import jcurses.system.CharColor;
import jcurses.system.Toolkit;
import org.s21.control.UserInput;
import org.s21.application.usecase.system.CheckGameOver;
import org.s21.domain.model.entity.creature.enemies.Ghost;
import org.s21.domain.model.entity.creature.enemies.Ogre;
import org.s21.domain.model.entity.creature.enemies.Snake;
import org.s21.domain.model.entity.creature.enemies.Vampire;
import org.s21.domain.model.entity.creature.enemies.Zombie;
import org.s21.infrastructure.config.Config;
import org.s21.infrastructure.generation.EntityGenerator;
import org.s21.domain.model.Entity;
import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.items.consumable.elixirs.Elixir;
import org.s21.domain.model.items.consumable.food.Food;
import org.s21.domain.model.items.consumable.scrolls.Scroll;
import org.s21.domain.model.items.weapons.Weapon;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;
import org.s21.infrastructure.generation.LevelGenerator;
import org.s21.ui.actions.UserAction;
import org.s21.ui.console.PrintGame;
import org.s21.application.usecase.system.GetInfo;

import java.util.Map;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        GameSession gameSession = new GameSession();
        gameSession.setLevel(LevelGenerator.generateLevel(gameSession));
        gameSession.setCurrentLevel(gameSession.getCurrentLevel() + 1);
        EntityGenerator.spawn(gameSession);

        Toolkit.init();

        gameLoop(gameSession);

        Toolkit.shutdown();
    }

    private static void gameLoop(GameSession gameSession) {

        checkScreen();

        PrintGame.printHelp();

        UserInput.getUserInput();
        PrintGame.clear();
        PrintGame.printFrame(cellToChar(GetInfo.getVisibleMap(gameSession)),
                GetInfo.getHeroStatus(gameSession), gameSession.getHero(), gameSession.getMessage());

        int inputCode;
        Map<Coordinate, Cell> updateMap = new HashMap<>();
        do {
            inputCode = UserInput.getUserInput().getCode();
            UserAction userAction = UserInput.selectUserAction(inputCode);
            if (userAction != null) {
                updateMap.clear();
                gameSession.setMessage("");
                updateMap = userAction.doAction(gameSession);
                if (updateMap.isEmpty()) {
                    PrintGame.clear();
                    updateMap = GetInfo.getVisibleMap(gameSession);
                }
                PrintGame.printFrame(cellToChar(updateMap), GetInfo.getHeroStatus(gameSession),
                        gameSession.getHero(), gameSession.getMessage());
            }
        } while (inputCode != Config.KEY_ESC && !CheckGameOver.checkGameOver(gameSession));

        PrintGame.printHeroStatus(" YOU ARE DEAD!!! GAME OVER!!!");
        UserInput.getUserInput();
    }

    private static void checkScreen() {
        CharColor colorWhite = new CharColor(CharColor.BLACK, CharColor.WHITE);
        boolean isScreenSizeOk = false;
        while (!isScreenSizeOk) {
            int screenHeight = Toolkit.getScreenHeight();
            int screenWidth = Toolkit.getScreenWidth();
            isScreenSizeOk = screenHeight >= Config.MAP_HEIGHT + 3 && screenWidth >= Config.MAP_WIDTH;
            if (!isScreenSizeOk) {
                Toolkit.printString("ScreenSize.is." + screenHeight + "x" + screenWidth, 0, 0, colorWhite);
                Toolkit.printString("Minimum.is." + (Config.MAP_HEIGHT + 2) + "x" + Config.MAP_WIDTH, 0, 1, colorWhite);
            }
        }
    }

    private static Map<Coordinate, Character> cellToChar(Map<Coordinate, Cell> srcMap) {
        Map<Coordinate, Character> resultMap = new HashMap<>();
        for (Map.Entry<Coordinate, Cell> entry : srcMap.entrySet()) {
            char c;
            if (!entry.getValue().isVisible() && !entry.getValue().isVisited()) {
                c = Config.FOG;
            } else if (entry.getValue().isOccupied() && entry.getValue().isVisible()) {
                Entity entity = entry.getValue().getEntity();
                c = switch (entity) {
                    case Hero hero -> Config.HERO;
                    case Zombie zombie -> Config.ZOMBIE;
                    case Vampire vampire -> Config.VAMPIRE;
                    case Ghost ghost -> Config.GHOST;
                    case Ogre ogre -> Config.OGRE;
                    case Snake snake -> Config.SNAKE;
                    case Food food -> Config.FOOD;
                    case Elixir elixir -> Config.ELIXIR;
                    case Scroll scroll -> Config.SCROLL;
                    case Weapon weapon -> Config.WEAPON;
                    case null, default -> Config.TREASURE;
                };
            } else {
                c =
                        switch (entry.getValue().getTerrarianType()) {
                            case WALL -> Config.WALL;
                            case FLOOR -> Config.ROOM;
                            case CORRIDOR -> Config.CORRIDOR;
                            case EXIT -> Config.EXIT;
                            default -> Config.VOID;
                        };
            }
            resultMap.put(entry.getKey(), c);
        }
        return resultMap;
    }

}