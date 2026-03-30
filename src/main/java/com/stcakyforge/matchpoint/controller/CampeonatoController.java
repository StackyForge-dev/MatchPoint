package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.request.CampeonatoRequestDto;
import com.stcakyforge.matchpoint.dtos.response.CampeonatoResponseDto;
import com.stcakyforge.matchpoint.dtos.response.JogadorResponseDto;
import com.stcakyforge.matchpoint.service.CampeonatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/champ")
public class CampeonatoController {

    private final CampeonatoService  campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @PostMapping
    public ResponseEntity<CampeonatoResponseDto> criarCampeonato(@RequestBody CampeonatoRequestDto campeonatoRequestDto) {
        return ResponseEntity.ok(campeonatoService.criarCampeonato(campeonatoRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CampeonatoResponseDto>> listarCampeonatos() {
        return ResponseEntity.ok(campeonatoService.listarCampeopnatos());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<JogadorResponseDto>> rankingJogadoresGeral() {
        return ResponseEntity.ok(campeonatoService.rankingJogadoresGeral());
    }

    @GetMapping("ranking/{id}")
    public ResponseEntity<List<JogadorResponseDto>> rankingJogadoresPorCampeonato(@PathVariable Long id) {
        return ResponseEntity.ok(campeonatoService.rankingJogadoresPorCampeonato(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampeonato(@PathVariable Long id) {
        campeonatoService.deletarCampeonato(id);
        return ResponseEntity.noContent().build();
    }

}
