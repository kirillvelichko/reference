package my.project;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

@SpringBootTest
public abstract class IntegrationTest {
    private static final PostgreSQLContainer<?> postgresContainer;

    static {
        postgresContainer = new PostgreSQLContainer<>("postgres:11.4")
                .withDatabaseName("database")
                .withUsername("username")
                .withPassword("password");
        postgresContainer.start();

        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(postgresContainer, ""), "db/user.sql");
    }

    @DynamicPropertySource
    private static void appProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.driverClassName", postgresContainer::getDriverClassName);
    }
}
