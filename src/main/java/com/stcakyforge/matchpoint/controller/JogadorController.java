package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.request.JogadorRequestDto;
import com.stcakyforge.matchpoint.dtos.response.JogadorResponseDto;
import com.stcakyforge.matchpoint.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player")
public class JogadorController {

    public final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public ResponseEntity<JogadorResponseDto> criarJogador(@RequestBody JogadorRequestDto jogadorRequestDto) {
        return ResponseEntity.ok(jogadorService.criarJogador(jogadorRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<JogadorResponseDto>> pegarJogadores() {
        return ResponseEntity.ok(jogadorService.pegarTodosJogadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorResponseDto> pegarJogadorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jogadorService.pegarJogadorPorId(id));
    }

    @GetMapping("/score/{id}")
    public ResponseEntity<Integer> saldoGols(@PathVariable Long id) {
        return ResponseEntity.ok(jogadorService.saldoGols(id));
    }

    @GetMapping("/champ/{id}")
    public ResponseEntity<List<JogadorResponseDto>> pegarJogadoresPorCampeopnato(@PathVariable Long id) throws Throwable {
        return ResponseEntity.ok(jogadorService.pegarJogadoresPorCampeopnato(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogador(@PathVariable Long id) {
        jogadorService.deletarJogador(id);
        return ResponseEntity.noContent().build();
    }

}
