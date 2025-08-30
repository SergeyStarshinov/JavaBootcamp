package org.s21.application.usecase.movement;

import org.s21.infrastructure.generation.EntityGenerator;
import org.s21.infrastructure.generation.LevelGenerator;
import org.s21.domain.model.world.GameSession;

public class HandleLevelTransition {
    public static void goToNewLevel(GameSession gameSession) {
        gameSession.setLevel(LevelGenerator.generateLevel(gameSession));
        int newLevel = gameSession.getCurrentLevel() + 1;
        gameSession.setCurrentLevel(newLevel);
        EntityGenerator.spawn(gameSession);
        gameSession.appendMessage("You have entered level " + newLevel);
    }
} 