package org.s21.infrastructure.config;

import java.util.Random;

public class Config {
    public static final Random RANDOM = new Random();

    public static final char ROOM = '.';
    public static final char CORRIDOR = '*';
    public static final char WALL = '#';
    public static final char EXIT = '%';
    public static final char VOID = ' ';
    public static final char HERO = '@';
    public static final char ZOMBIE = 'z';
    public static final char VAMPIRE = 'v';
    public static final char GHOST = 'g';
    public static final char OGRE = 'O';
    public static final char SNAKE = 's';
    public static final char FOOD = 'F';
    public static final char ELIXIR = 'E';
    public static final char SCROLL = 'I';
    public static final char WEAPON = 'W';
    public static final char TREASURE = '$';
    public static final char FOG = '/';

    public static final int MAP_HEIGHT = 30;
    public static final int MAP_WIDTH = 90;

    public static final int ROOMS_IN_HEIGHT = 3;
    public static final int ROOMS_IN_WIDTH = 3;
    public static final int ROOM_MIN_SIZE = 5;
    public static final int ROOM_MAX_SIZE = 10;

    public static final int GRID_COLS = 3;
    public static final int GRID_ROWS = 3;

    public static final int GRID_WIDTH = MAP_WIDTH / GRID_COLS;
    public static final int GRID_HEIGHT = MAP_HEIGHT / GRID_ROWS;

    public static final Double BASE_CRITICAL_COEFFICIENT = 1.2;
    public static final Double BASE_CHANCE_CRITICAL_DAMAGE = 0.005;

    public static final int BASE_CHANCE_MODIFIER_ATTACK = 5; // шанс модифицированной атаки(усыпить, снять часть здоровья)
    public static final int VAMPIRE_STEAL_DAMAGE = 5; // Урон при воровстве здоровья

    public static final int LOW = 2;
    public static final int MID = 4;
    public static final int HIGH = 6;
    public static final int EXTRA_HIGH = 9;

    public static final int BASE_COUNT_ITEM_IN_DUNGEON = 15;
    public static final int BASE_COUNT_ENEMY_IN_DUNGEON = 12;


    // Свитки, еда, элексиры
    public static final int MIN_POWER = 60;
    public static final int MAX_POWER = 101;

    public static final int DEFAULT_TIME= 5;
    public static final int MIN_TIME = 1;
    public static final int MAX_TIME = 10;

    public static final int DEFAULT_DAMAGE = 5;
    public static final int MIN_DAMAGE = 3;
    public static final int MAX_DAMAGE = 10;


    public static final int KEY_ESC = 27;
}
