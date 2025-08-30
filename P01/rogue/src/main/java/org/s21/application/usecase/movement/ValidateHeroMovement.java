package org.s21.application.usecase.movement;

import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.GameSession;

public class ValidateHeroMovement {
    
    public static boolean canMove(GameSession gameSession, Coordinate newHeroCoordinate) {
        Cell newPlace = gameSession.getLevel().getCellBy(newHeroCoordinate);
        
        return newPlace != null && 
               newPlace.getTerrarianType() != TerrarianType.WALL;
    }
    
    public static boolean isExit(GameSession gameSession, Coordinate newHeroCoordinate) {
        Cell newPlace = gameSession.getLevel().getCellBy(newHeroCoordinate);
        
        return newPlace != null && 
               newPlace.getTerrarianType() == TerrarianType.EXIT;
    }

    public static boolean isItem(GameSession gameSession, Coordinate newHeroCoordinate) {
        Cell newPlace = gameSession.getLevel().getCellBy(newHeroCoordinate);

        return newPlace.getEntity() instanceof Item;
    }

    public static boolean isEnemy(GameSession gameSession, Coordinate newHeroCoordinate) {
        Cell newPlace = gameSession.getLevel().getCellBy(newHeroCoordinate);

        return newPlace.getEntity() instanceof Enemy;
    }
} 