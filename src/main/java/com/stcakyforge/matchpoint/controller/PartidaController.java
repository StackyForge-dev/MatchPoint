package com.stcakyforge.matchpoint.controller;

import com.stcakyforge.matchpoint.dtos.response.PartidaResponseDto;
import com.stcakyforge.matchpoint.service.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/plays")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<PartidaResponseDto> criarPartida (@RequestBody Long idJogador1, @RequestBody Long idJogador2) {
        return ResponseEntity.ok(partidaService.criarPartida(idJogador1, idJogador2));
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
