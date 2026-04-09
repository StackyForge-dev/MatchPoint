package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.config.TokenConfig;
import com.stcakyforge.matchpoint.dtos.request.LoginRequestDto;
import com.stcakyforge.matchpoint.dtos.request.RegisterUserRequestDto;
import com.stcakyforge.matchpoint.dtos.response.LoginResponseDto;
import com.stcakyforge.matchpoint.dtos.response.RegisterUserResponseDto;

import com.stcakyforge.matchpoint.model.Usuario;
import com.stcakyforge.matchpoint.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@Valid @RequestBody LoginRequestDto request) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register (@Valid @RequestBody RegisterUserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(request));
    }

}
