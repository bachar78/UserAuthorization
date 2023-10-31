package com.bachar.recipes.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUserName_whenUserExists_returnsUser() {
        User user = new User();
        user.setUsername("test-user");
        user.setDisplayName("test-display");
        user.setPassword("P@4ssword");
        testEntityManager.persist(user);
        User userInDB = userRepository.findByUsername("test-user");
        assertThat(userInDB).isNotNull();
    }

    @Test
    public void findByUserName_whenUserNotExists_returnsNull() {
        User userInDB = userRepository.findByUsername("test-user");
        assertThat(userInDB).isNull();
    }
}
