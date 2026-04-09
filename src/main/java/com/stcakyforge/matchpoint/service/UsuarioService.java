package com.stcakyforge.matchpoint.service;

import com.stcakyforge.matchpoint.dtos.request.EmailRequestDto;
import com.stcakyforge.matchpoint.dtos.request.RegisterUserRequestDto;
import com.stcakyforge.matchpoint.dtos.request.SenhaRequestDto;
import com.stcakyforge.matchpoint.dtos.request.UsuarioRequestDto;
import com.stcakyforge.matchpoint.dtos.response.RegisterUserResponseDto;
import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.mapper.UsuarioMapper;
import com.stcakyforge.matchpoint.model.Usuario;
import com.stcakyforge.matchpoint.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper mapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponseDto registrarUsuario(RegisterUserRequestDto requestDto){
        if (usuarioRepository.existsByEmail(requestDto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(requestDto.username());
        usuario.setEmail(requestDto.email());
        usuario.setSenha(passwordEncoder.encode(requestDto.senha()));

        usuarioRepository.save((usuario));

        return new RegisterUserResponseDto(
                usuario.getUsername(),
                usuario.getEmail()
        );
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

    public UsuarioResponseDto atualizarUsernameUsuario(Long id, UsuarioRequestDto usuario){

        Usuario oldUsuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados"));

        oldUsuario.setId(id);
        oldUsuario.setNome(!Objects.equals(usuario.username(), oldUsuario.getUsername()) ? usuario.username() : oldUsuario.getUsername());

        return mapper.toDto(usuarioRepository.save(oldUsuario));
    }

    public void atualizarSenha(Long id, SenhaRequestDto novaSenha){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados"));

        String passwordEnc = passwordEncoder.encode(novaSenha.novaSenha());

        usuario.setSenha(passwordEnc);
        usuarioRepository.save(usuario);
    }

    public void atualizarEmail(Long id, EmailRequestDto novoEmail){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado no banco de dados"));

        usuario.setEmail(novoEmail.novoEmail());
        usuarioRepository.save(usuario);
    }
}
