package org.s21.datasource.service;

import org.s21.domain.model.Game;

import java.util.UUID;

public interface DatasourceService {

  void saveGame(Game game);
  Game loadGame(UUID uuid);
  void deleteGame(UUID uuid);
}
