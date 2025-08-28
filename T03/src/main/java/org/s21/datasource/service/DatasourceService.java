package org.s21.datasource.service;

import org.s21.datasource.mapper.DatasourceMapper;
import org.s21.datasource.repository.DatasourceRepository;
import org.s21.domain.model.Game;

import java.util.UUID;

public class DatasourceService {

  private final DatasourceRepository repository;

  public DatasourceService(DatasourceRepository repository) {
    this.repository = repository;
  }

  public void saveGame(Game game) {
    repository.saveGameData(DatasourceMapper.fromGameToGameData(game));
  }

  public Game loadgame(UUID uuid) {
    return DatasourceMapper.fromGameDataToGame(repository.loadGameData(uuid));
  }
}
