package org.s21.di;

import org.s21.datasource.model.Storage;
import org.s21.datasource.repository.DatasourceRepository;
import org.s21.datasource.service.DatasourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Storage storage() {
    return new Storage();
  }

  @Bean
  public DatasourceRepository datasourceRepository(Storage storage) {
    return new DatasourceRepository(storage);
  }

  @Bean
  public DatasourceService datasourceService(DatasourceRepository repository) {
    return new DatasourceService(repository);
  }

}
