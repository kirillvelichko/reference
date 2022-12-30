package my.project.database.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "app_config")
public class AppConfig {
    @Id
    private Long id;
    private String name;
    private String value;
    private String description;
}
