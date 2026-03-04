package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.request.SenhaRequestDto;
import com.stcakyforge.matchpoint.dtos.request.UsuarioRequestDto;
import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        return usuarioService.salvarUsuario(usuarioRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> pegarUsuarios() {
        return ResponseEntity.ok(usuarioService.listaUsuarios());
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto pegarUsuario(@PathVariable Long id) {
        return usuarioService.pegarUsuarioPorId(id);
    }

    @PutMapping("/{id}/edit/password")
    public ResponseEntity<Void> atualizarSenhaUsuario(@PathVariable Long id, @RequestBody @Valid SenhaRequestDto novaSenha) {
        usuarioService.atualizarSenha(id, novaSenha);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuarioRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}