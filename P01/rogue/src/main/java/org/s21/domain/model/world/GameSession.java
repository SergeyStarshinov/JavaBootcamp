package org.s21.domain.model.world;

import org.s21.domain.model.Backpack;
import org.s21.domain.model.entity.creature.Hero;

public class GameSession {
    private Hero hero;
    private Backpack backpack;
    private Level level;
    private int currentLevel;
    private int countMoves;
    private String message;

    public GameSession() {
        hero = new Hero(null, 100, 100, 100, 100);
        backpack = new Backpack();
        currentLevel = 0;
        message = "You have entered level 1";
    }


    public Hero getHero() {
        return hero;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void appendMessage(String message) {
        this.message += message;
    }

}
