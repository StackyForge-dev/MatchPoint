package com.stcakyforge.matchpoint.service;


import com.stcakyforge.matchpoint.dtos.response.PartidaResponseDto;
import com.stcakyforge.matchpoint.mapper.PartidaMapper;
import com.stcakyforge.matchpoint.model.Jogador;
import com.stcakyforge.matchpoint.model.Partida;
import com.stcakyforge.matchpoint.repository.JogadorRepository;
import com.stcakyforge.matchpoint.repository.PartidaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {


    private final PartidaRepository partidaRepository;
    private final JogadorRepository jogadorRepository;
    private final PartidaMapper partidaMapper;

    public PartidaService(PartidaRepository partidaRepository, PartidaMapper partidaMapper, JogadorRepository jogadorRepository) {
        this.partidaRepository = partidaRepository;
        this.partidaMapper = partidaMapper;
        this.jogadorRepository = jogadorRepository;
    }

    public ResponseEntity<PartidaResponseDto> criarPartida(Long idJogador1, Long idJogador2) {
        Partida newPartida = new Partida();

        newPartida.setJogador1(jogadorRepository.findById(idJogador1).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado")));
        newPartida.setJogador1(jogadorRepository.findById(idJogador2).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado")));

        return ResponseEntity.ok(partidaMapper.toDto(partidaRepository.save(newPartida)));
    }

    public ResponseEntity<PartidaResponseDto> pegarPartidaPorId(Long id) {
        return ResponseEntity.ok(partidaMapper.toDto(partidaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado"))));
    }

    public ResponseEntity<Void> deletarPartida(Long id) {
        if(partidaRepository.existsById(id)) {
            partidaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<PartidaResponseDto> placarGols(Long idPartida, int golsJogador1, int golsJogador2) {
        Partida partida = partidaRepository.findById(idPartida).orElseThrow(() -> new EntityNotFoundException("Partida inexistente"));

        partida.setGolsJogador1(golsJogador1);
        partida.setGolsJogador2(golsJogador2);

        Jogador jogador1 = jogadorRepository.findById(partida.getJogador1().getId()).orElseThrow(() -> new EntityNotFoundException("Jogador inexistente"));
        Jogador jogador2 = jogadorRepository.findById(partida.getJogador2().getId()).orElseThrow(() -> new EntityNotFoundException("Jogador inexistente"));

        jogador1.setGolsMarcados(jogador1.getGolsMarcados()+golsJogador1);
        jogador2.setGolsMarcados(jogador2.getGolsMarcados()+golsJogador1);

        jogador1.setGolsSofridos(partida.getGolsJogador2());
        jogador2.setGolsSofridos(partida.getGolsJogador1());

        jogadorRepository.save(jogador1);
        jogadorRepository.save(jogador2);

        return ResponseEntity.ok(partidaMapper.toDto(partidaRepository.save(partida)));
    }

    public ResponseEntity<PartidaResponseDto> placarCartoesAmarelos(Long idPartida, int cartoesJogador1, int cartoesJogador2) {
        Partida partida = partidaRepository.findById(idPartida).orElseThrow(() -> new EntityNotFoundException("Partida inexistente"));

        partida.setCartaoAmareloJogador1(cartoesJogador1);
        partida.setCartaoAmareloJogador2(cartoesJogador2);

        return ResponseEntity.ok(partidaMapper.toDto(partidaRepository.save(partida)));
    }
}
