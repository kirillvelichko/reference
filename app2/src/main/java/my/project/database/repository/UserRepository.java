package my.project.database.repository;

import my.project.database.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    String UPDATE_LAST_NAME_BY_FIRST_NAME = """
            update user
            set last_name = :lastName
            where first_name = :firstName
            """;

    List<User> getAllByFirstName(String firstName);

    @Modifying
    @Query(UPDATE_LAST_NAME_BY_FIRST_NAME)
    boolean updateLastNameByFirstName(String lastName, String firstName);
}
