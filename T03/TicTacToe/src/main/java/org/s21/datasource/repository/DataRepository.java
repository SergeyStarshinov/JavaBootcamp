package org.s21.datasource.repository;

import org.s21.datasource.model.GameData;
import java.util.UUID;

public interface DataRepository {

  void saveGame(GameData game);

  GameData loadGame(UUID uuid);

  void deleteGame(UUID uuid);
}
