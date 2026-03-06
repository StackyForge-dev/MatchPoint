package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.request.EmailRequestDto;
import com.stcakyforge.matchpoint.dtos.request.SenhaRequestDto;
import com.stcakyforge.matchpoint.dtos.request.UsuarioRequestDto;
import com.stcakyforge.matchpoint.dtos.response.UsuarioResponseDto;
import com.stcakyforge.matchpoint.service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<UsuarioResponseDto> pegarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.pegarUsuarioPorId(id));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        usuarioService.atualizarUsuario(id, usuarioRequestDto);
        return ResponseEntity.ok().build();
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