package my.project.database.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "user")
public class UserEntity {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
}
