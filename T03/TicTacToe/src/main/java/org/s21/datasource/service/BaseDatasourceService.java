package org.s21.datasource.service;

import org.s21.datasource.mapper.DatasourceMapper;
import org.s21.datasource.repository.DataRepository;
import org.s21.domain.model.Game;

import java.util.UUID;

public class BaseDatasourceService implements DatasourceService{

  private final DataRepository dataRepository;

  public BaseDatasourceService(DataRepository repository) {
    this.dataRepository = repository;
  }

  public void saveGame(Game game) {
    dataRepository.saveGame(DatasourceMapper.fromGameToGameData(game));
  }

  public Game loadGame(UUID uuid) {
    return DatasourceMapper.fromGameDataToGame(dataRepository.loadGame(uuid));
  }

  public void deleteGame(UUID uuid) {
    dataRepository.deleteGame(uuid);
  }
}
