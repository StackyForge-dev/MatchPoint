package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.request.PartidaRequestDto;
import com.stcakyforge.matchpoint.dtos.response.PartidaResponseDto;
import com.stcakyforge.matchpoint.dtos.response.PegarPartidasDto;
import com.stcakyforge.matchpoint.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plays")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<PartidaResponseDto> criarPartida (@RequestBody PartidaRequestDto request) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.criarPartida
            (
                request.idJogador1(),
                request.idJogador2()
            )
        );
    }

    @GetMapping
    public ResponseEntity<List<PegarPartidasDto>> pegarPartidas () {
        return ResponseEntity.ok(partidaService.pegarPartidasPorCampeonatos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponseDto> pegarPartidaPorId (@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.pegarPartidaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPartida(@PathVariable Long id) {
        if(partidaService.partidaExistePorId(id)) {
            partidaService.deletarPartida(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/score/{id}")
    public ResponseEntity<PartidaResponseDto> placarGols(@PathVariable Long id, int golsJogador1, int golsJogador2) {
        return ResponseEntity.ok(partidaService.placarGols(id, golsJogador1, golsJogador2));
    }

    @PostMapping("/score/{id}/cards")
    public ResponseEntity<PartidaResponseDto> placarCartoesAmarelos(@PathVariable Long id, int cartoesJogador1, int cartoesJogador2) {
        return ResponseEntity.ok(partidaService.placarCartoesAmarelos(id, cartoesJogador1, cartoesJogador2));
    }



}
