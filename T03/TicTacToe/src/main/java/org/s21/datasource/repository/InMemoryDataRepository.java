package org.s21.datasource.repository;

import org.s21.datasource.model.GameData;
import org.s21.datasource.model.Storage;

import java.util.UUID;

public class InMemoryDataRepository extends DataRepository {

  private final Storage storage;

  public InMemoryDataRepository(Storage storage) {
    this.storage = storage;
  }

  @Override
  public void saveGame(GameData game) {
    storage.getStore().put(game.uuid(), game.gameBoard());
  }

  @Override
  public GameData loadGame(UUID uuid) {
    return new GameData(uuid, storage.getStore().get(uuid));
  }

  @Override
  public void deleteGame(UUID uuid) {
    storage.getStore().remove(uuid);
  }
}
