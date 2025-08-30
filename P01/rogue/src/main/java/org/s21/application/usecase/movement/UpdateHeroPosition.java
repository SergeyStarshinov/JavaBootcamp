package org.s21.application.usecase.movement;

import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Direction;
import org.s21.domain.model.world.GameSession;

import java.util.HashMap;
import java.util.Map;

public class UpdateHeroPosition {
    
    public static Map<Coordinate, Cell> updatePosition(GameSession gameSession, Direction direction) {
        Map<Coordinate, Cell> updateMap = new HashMap<>();
        Coordinate currentHeroCoordinate = gameSession.getHero().getCoordinate();
        Cell oldPlace = gameSession.getLevel().getCellBy(currentHeroCoordinate);
        
        int newX = currentHeroCoordinate.getX() + direction.getDeltaX();
        int newY = currentHeroCoordinate.getY() + direction.getDeltaY();
        Coordinate newHeroCoordinate = new Coordinate(newX, newY);
        Cell newPlace = gameSession.getLevel().getCellBy(newHeroCoordinate);
        
        oldPlace.changeOccupied();
        oldPlace.setEntity(null);
        updateMap.put(currentHeroCoordinate, oldPlace);
        
        gameSession.getHero().setCoordinate(newHeroCoordinate);
        newPlace.changeOccupied();
        newPlace.setEntity(gameSession.getHero());
        newPlace.setVisited();
        updateMap.put(newHeroCoordinate, newPlace);
        
        return updateMap;
    }
} 