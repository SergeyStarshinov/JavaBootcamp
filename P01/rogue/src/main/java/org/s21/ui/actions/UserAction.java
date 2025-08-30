package org.s21.ui.actions;

import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;

import java.util.Map;

public abstract class UserAction {
  public abstract Map<Coordinate, Cell> doAction(GameSession gameSession);
}
