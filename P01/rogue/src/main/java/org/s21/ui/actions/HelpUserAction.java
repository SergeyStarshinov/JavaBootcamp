package org.s21.ui.actions;

import org.s21.control.UserInput;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.world.GameSession;
import org.s21.ui.console.PrintGame;

import java.util.HashMap;
import java.util.Map;

public class HelpUserAction extends UserAction{
  @Override
  public Map<Coordinate, Cell> doAction(GameSession gameSession) {
    PrintGame.printHelp();
    UserInput.getUserInput();
    return new HashMap<>();
  }
}
