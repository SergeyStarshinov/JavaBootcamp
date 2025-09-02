package org.s21.di;

import org.s21.datasource.model.Storage;
import org.s21.datasource.repository.InMemoryDataRepository;
import org.s21.datasource.repository.DataRepository;
import org.s21.datasource.service.BaseDatasourceService;
import org.s21.datasource.service.DatasourceService;
import org.s21.domain.service.GameService;
import org.s21.domain.service.MinMaxGameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Storage storage() {
    return new Storage();
  }

  @Bean
  public DataRepository datasourceRepository(Storage storage) {
    return new InMemoryDataRepository(storage);
  }

  @Bean
  public DatasourceService datasourceService(DataRepository repository) {
    return new BaseDatasourceService(repository);
  }

  @Bean
  public GameService gameService(DatasourceService datasourceService) {
    return new MinMaxGameService(datasourceService);
  }
}
