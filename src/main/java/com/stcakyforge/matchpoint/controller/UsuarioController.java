package com.stcakyforge.matchpoint.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stcakyforge.matchpoint.dtos.request.*;
import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> pegarUsuarios() {
        return ResponseEntity.ok(usuarioService.listaUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> pegarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.pegarUsuarioPorId(id));
    }

    @PutMapping("/{id}/edit/username")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.atualizarUsernameUsuario(id, usuarioRequestDto));
    }

    @PutMapping("/{id}/edit/password")
    public ResponseEntity<Void> atualizarSenhaUsuario(@PathVariable Long id, @RequestBody @Valid SenhaRequestDto novaSenha) {
        usuarioService.atualizarSenha(id, novaSenha);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/edit/email")
    public ResponseEntity<Void> atualizarEmailUsuario(@PathVariable Long id, @RequestBody @Valid EmailRequestDto novoEmail) {
        usuarioService.atualizarEmail(id, novoEmail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }
}