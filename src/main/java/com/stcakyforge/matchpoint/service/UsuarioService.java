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
import com.stcakyforge.matchpoint.Exception.ConflictException;
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
            throw new ConflictException();
        }

        Usuario usuario = new Usuario();
        usuario.setName(requestDto.name());
        usuario.setEmail(requestDto.email());
        usuario.setUserPassword(passwordEncoder.encode(requestDto.userPassword()));

        usuarioRepository.save((usuario));

        return new RegisterUserResponseDto(
                usuario.getName(),
                usuario.getEmail()
        );
    }

    public List<UsuarioResponseDto> listaUsuarios(){
        return mapper.toDto(usuarioRepository.findAll());
    }

    public UsuarioResponseDto pegarUsuarioPorId(Long id){
        return mapper.toDto(usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDto atualizarUsernameUsuario(Long id, UsuarioRequestDto usuario){

        Usuario oldUsuario = usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        oldUsuario.setId(id);
        oldUsuario.setName(!Objects.equals(usuario.name(), oldUsuario.getUsername()) ? usuario.name() : oldUsuario.getUsername());

        return mapper.toDto(usuarioRepository.save(oldUsuario));
    }

    public void atualizarSenha(Long id, SenhaRequestDto novaSenha){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        String passwordEnc = passwordEncoder.encode(novaSenha.novaSenha());

        usuario.setUserPassword(passwordEnc);
        usuarioRepository.save(usuario);
    }

    public void atualizarEmail(Long id, EmailRequestDto novoEmail){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        usuario.setEmail(novoEmail.novoEmail());
        usuarioRepository.save(usuario);
    }
}
