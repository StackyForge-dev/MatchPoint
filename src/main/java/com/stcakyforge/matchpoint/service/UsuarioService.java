package com.stcakyforge.matchpoint.service;

import com.stcakyforge.matchpoint.config.PasswordConfig;
import com.stcakyforge.matchpoint.dtos.request.EmailRequestDto;
import com.stcakyforge.matchpoint.dtos.request.SenhaRequestDto;
import com.stcakyforge.matchpoint.dtos.request.UsuarioRequestDto;
import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.mapper.UsuarioMapper;
import com.stcakyforge.matchpoint.model.Usuario;
import com.stcakyforge.matchpoint.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final  PasswordConfig passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper mapper, PasswordConfig passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<UsuarioResponseDto> salvarUsuario(UsuarioRequestDto requestDto){
        if (usuarioRepository.existsByEmail(requestDto.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        String passwordEnc = passwordEncoder.passwordEncoder().encode(requestDto.senha());

        UsuarioRequestDto user = new UsuarioRequestDto(
                requestDto.username(),
                requestDto.email(),
                passwordEnc
        );
        return ResponseEntity.ok(mapper.toDto(usuarioRepository.save(mapper.toEntity(user))));
    }

    public List<UsuarioResponseDto> listaUsuarios(){
        return mapper.toDto(usuarioRepository.findAll());
    }

    public UsuarioResponseDto pegarUsuarioPorId(Long id){
        return mapper.toDto(usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados")));
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioRequestDto usuario){

        Usuario oldUsuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados"));

        oldUsuario.setId(id);
        oldUsuario.setUsername(!Objects.equals(usuario.username(), oldUsuario.getUsername()) ? usuario.username() : oldUsuario.getUsername());

        return mapper.toDto(usuarioRepository.save(oldUsuario));
    }

    public void atualizarSenha(Long id, SenhaRequestDto novaSenha){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados"));

        usuario.setSenha(novaSenha.novaSenha());
        usuarioRepository.save(usuario);
    }

    public void atualizarEmail(Long id, EmailRequestDto novoEmail){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados"));

        usuario.setEmail(novoEmail.novoEmail());
        usuarioRepository.save(usuario);
    }
}
