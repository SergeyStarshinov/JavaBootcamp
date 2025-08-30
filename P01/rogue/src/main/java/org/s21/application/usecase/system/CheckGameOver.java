package org.s21.application.usecase.system;

import org.s21.domain.model.world.GameSession;

public class CheckGameOver {
  public static boolean checkGameOver(GameSession gameSession) {
    return gameSession.getCurrentLevel() > 21 || gameSession.getHero().getStats().getHealth() <= 0;
  }
}
