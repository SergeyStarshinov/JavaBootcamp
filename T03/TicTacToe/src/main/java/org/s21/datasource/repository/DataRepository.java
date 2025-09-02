package org.s21.datasource.repository;

import org.s21.datasource.model.GameData;
import java.util.UUID;

public abstract class DataRepository {

  public abstract void saveGame(GameData game);

  public abstract GameData loadGame(UUID uuid);

  public abstract void deleteGame(UUID uuid);
}
