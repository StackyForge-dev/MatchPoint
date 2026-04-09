package com.stcakyforge.matchpoint.repository;
import com.stcakyforge.matchpoint.dtos.request.UsuarioRequestDto;
import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Usuário foi encontrado com sucesso no Bando de Dados")
    void findByEmailCaseTrue() {
        String email = "joão@email.com";

        UsuarioRequestDto newUsuario = new UsuarioRequestDto(
                "João da Silva",
                email,
                "senha123"
        );
        this.createUsuario(newUsuario);

        Optional<UserDetails> foundedUsuario = usuarioRepository.findByEmail(email);

        assertThat(foundedUsuario.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Usuário não foi encontrado no Bando de Dados")
    void findByEmailCaseFalse() {
        String email = "joão@email.com";
        Optional<UserDetails> foundedUsuario = usuarioRepository.findByEmail(email);

        assertThat(foundedUsuario.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Um usuário com este email está cadastrado")
    void existsByEmailCaseTrue() {
        String email = "joão@email.com";

        UsuarioRequestDto newUsuario = new UsuarioRequestDto(
                "João da Silva",
                email,
                "senha123"
        );
        this.createUsuario(newUsuario);

        boolean foundedUsuario = usuarioRepository.existsByEmail(email);

        assertThat(foundedUsuario).isTrue();
    }

    @Test
    @DisplayName("Nenhum usuário com este email está cadastrado")
    void existsByEmailCaseFalse() {
        String email = "joão@email.com";

        boolean foundedUsuario = usuarioRepository.existsByEmail(email);

        assertThat(foundedUsuario).isFalse();
    }

    private void createUsuario(UsuarioRequestDto request) {
        Usuario newUsuario = new Usuario();
        newUsuario.setNome(request.username());
        newUsuario.setEmail(request.email());
        newUsuario.setSenha(request.senha());

        entityManager.persist(newUsuario);
    }
}