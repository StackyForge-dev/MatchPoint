package com.stcakyforge.matchpoint.repository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Test
    void findByEmail() {
    }

    @Test
    void existsByEmail() {
    }
}