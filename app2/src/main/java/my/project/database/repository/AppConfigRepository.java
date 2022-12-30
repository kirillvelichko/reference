package my.project.database.repository;

import my.project.database.entity.AppConfig;
import org.springframework.data.repository.CrudRepository;

public interface AppConfigRepository extends CrudRepository<AppConfig, Long> {
    AppConfig getByName(String name);
}
