package my.project.database.repository;

import my.project.IntegrationTest;
import my.project.database.entity.UserEntity;
import my.project.database.entity.user.Sex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static my.project.database.entity.user.Sex.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
class UserRepositoryTest extends IntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        var user = new UserEntity();
        user.setFirstName("Petr");
        user.setLastName("Petrov");
        user.setSex(MALE);
        user.setBirthDate(LocalDate.of(1990, 10, 15));
        userRepository.save(user);
    }

    @Test
    void getAllByFirstNameAndLastName_entityMapping() {
        var userList = userRepository.getAllByFirstNameAndLastName("Petr", "Petrov");
        assertEquals(1, userList.size());
        var user = userList.get(0);
        assertNotNull(user.getId());
        assertEquals("Petr", user.getFirstName());
        assertEquals("Petrov", user.getLastName());
        assertEquals(MALE, user.getSex());
        assertEquals(LocalDate.of(1990, 10, 15), user.getBirthDate());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }

    @Test
    void updateLastNameByFirstName() {
    }
}