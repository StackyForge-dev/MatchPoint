package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.request.PartidaRequestDto;
import com.stcakyforge.matchpoint.dtos.response.PartidaResponseDto;
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
    public ResponseEntity<List<PartidaResponseDto>> pegarPartidas () {
        return ResponseEntity.ok(partidaService.pegarPartidas());
    }

    @GetMapping("/champ/{id}")
    public ResponseEntity<List<PartidaResponseDto>> pegarPartidasPorCampeonato (@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.pegarPartidasPorCampeonato(id));
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
    public ResponseEntity<PartidaResponseDto> placarGols(@PathVariable Long id, @RequestBody PartidaRequestDto request) {
        return ResponseEntity.ok(partidaService.fazerGol(
                id,
                request.golsJogador1(),
                request.golsJogador2())
        );
    }

    @PostMapping("/score/{id}/cards")
    public ResponseEntity<PartidaResponseDto> placarCartoesAmarelos(@PathVariable Long id, @RequestBody PartidaRequestDto request) {
        return ResponseEntity.ok(partidaService.placarCartoesAmarelos(
                id,
                request.cartaoAmareloJogador1(),
                request.cartaoAmareloJogador2())
        );
    }
}
