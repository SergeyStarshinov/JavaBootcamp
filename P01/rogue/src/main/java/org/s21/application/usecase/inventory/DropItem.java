package org.s21.application.usecase.inventory;

import org.s21.domain.model.items.Item;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.GameSession;

import static org.s21.infrastructure.config.Config.RANDOM;

public class DropItem {
  public static void execute(GameSession gameSession, Item item) {
      Coordinate heroCoordinate = gameSession.getHero().getCoordinate();
      Coordinate[] neighbors = new Coordinate[8];
      int countNeighbors = 0;
      for (int x = -1; x <= 1; ++x) {
        for (int y = -1; y <= 1; ++y) {
          Coordinate nextCoordinate = new Coordinate(heroCoordinate.getX() + x, heroCoordinate.getY() + y);
          Cell cell = gameSession.getLevel().getCellBy(nextCoordinate);
        if (cell != null && cell.getTerrarianType() != TerrarianType.WALL && !cell.isOccupied()) {
          neighbors[countNeighbors] = nextCoordinate;
          ++countNeighbors;
        }
        }
      }
      Coordinate dropCoordinate = neighbors[RANDOM.nextInt(countNeighbors)];
      item.setCoordinate(dropCoordinate);
      gameSession.getLevel().addEntity(item);
  }

}
