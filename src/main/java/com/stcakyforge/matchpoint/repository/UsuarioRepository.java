package com.stcakyforge.matchpoint.repository;

import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<UserDetails> findByEmail(String email);
    boolean existsByEmail(String email);
}
