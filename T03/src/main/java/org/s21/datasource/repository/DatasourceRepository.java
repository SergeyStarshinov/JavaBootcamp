package org.s21.datasource.repository;

import org.s21.datasource.model.GameData;
import org.s21.datasource.model.Storage;

import java.util.UUID;

public class DatasourceRepository {

  private final Storage storage;

  public DatasourceRepository(Storage storage) {
    this.storage = storage;
  }

  public void saveGameData(GameData gamedata) {
    storage.getStore().put(gamedata.uuid(), gamedata.gameBoard());
  }

  public GameData loadGameData(UUID uuid) {
    return new GameData(uuid, storage.getStore().get(uuid));
  };

}
