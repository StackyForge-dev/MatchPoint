package com.stcakyforge.matchpoint.service;


import com.stcakyforge.matchpoint.dtos.response.PartidaResponseDto;
import com.stcakyforge.matchpoint.mapper.PartidaMapper;
import com.stcakyforge.matchpoint.model.Campeonato;
import com.stcakyforge.matchpoint.model.Jogador;
import com.stcakyforge.matchpoint.model.Partida;
import com.stcakyforge.matchpoint.repository.CampeonatoRepository;
import com.stcakyforge.matchpoint.repository.JogadorRepository;
import com.stcakyforge.matchpoint.repository.PartidaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PartidaService {


    private final PartidaRepository partidaRepository;
    private final JogadorRepository jogadorRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final PartidaMapper partidaMapper;

    public PartidaService(PartidaRepository partidaRepository, PartidaMapper partidaMapper, CampeonatoRepository campeonatoRepository, JogadorRepository jogadorRepository) {
        this.partidaRepository = partidaRepository;
        this.partidaMapper = partidaMapper;
        this.campeonatoRepository = campeonatoRepository;
        this.jogadorRepository = jogadorRepository;
    }

    public PartidaResponseDto criarPartida(Long idJogador1, Long idJogador2) throws AccessDeniedException {
        Partida newPartida = new Partida();
        Jogador player1 = jogadorRepository.findById(idJogador1).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado"));
        Jogador player2 = jogadorRepository.findById(idJogador2).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado"));

        if (!player1.getCampeonato().getId().equals(player2.getCampeonato().getId())) {
            throw new AccessDeniedException("Não é possível criar partida de jogadores de campeonatos diferentes!");
        }
        newPartida.setJogador1(player1);
        newPartida.setJogador2(player2);

        player1.setPartidasTotais(player1.getPartidasTotais() + 1);
        player1.setPartidasComoJogador1(player1.getPartidasComoJogador1() + 1);
        player2.setPartidasTotais(player2.getPartidasTotais() + 1);
        player2.setPartidasComoJogador2(player2.getPartidasComoJogador2() + 1);

        jogadorRepository.save(player1);
        jogadorRepository.save(player2);

        newPartida.setCampeonato(campeonatoRepository.findById(player1.getCampeonato().getId()).orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado")));

        newPartida.setGolsJogador1(0);
        newPartida.setGolsJogador2(0);
        newPartida.setCartaoAmareloJogador1(0);
        newPartida.setCartaoAmareloJogador2(0);

        return partidaMapper.toDto(partidaRepository.save(newPartida));
    }

    public PartidaResponseDto pegarPartidaPorId(Long id) {
        return partidaMapper.toDto(partidaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado")));
    }

    public List<PartidaResponseDto> pegarPartidas () {
        return partidaMapper.toDto(partidaRepository.findAll());
    }

    public List<PartidaResponseDto> pegarPartidasPorCampeonato (Long id) {

        return partidaMapper.toDto(partidaRepository.findByCampeonatoId(id));
    }

    public void deletarPartida(Long id) {
        partidaRepository.deleteById(id);
    }

    public PartidaResponseDto fazerGol(Long id, int golsJogador1, int golsJogador2) {
        Partida partida = partidaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partida inexistente"));

        partida.setGolsJogador1(golsJogador1);
        partida.setGolsJogador2(golsJogador2);

        Jogador jogador1 = jogadorRepository.findById(partida.getJogador1().getId()).orElseThrow(() -> new EntityNotFoundException("Jogador inexistente"));
        Jogador jogador2 = jogadorRepository.findById(partida.getJogador2().getId()).orElseThrow(() -> new EntityNotFoundException("Jogador inexistente"));

        jogador1.setGolsMarcados(jogador1.getGolsMarcados()+golsJogador1);
        jogador2.setGolsMarcados(jogador2.getGolsMarcados()+golsJogador1);

        jogador1.setGolsSofridos(partida.getGolsJogador2());
        jogador2.setGolsSofridos(partida.getGolsJogador1());

        partida.setCartaoAmareloJogador1(jogador1.getCartoesAmarelos());
        partida.setCartaoAmareloJogador2(jogador2.getCartoesAmarelos());

        jogadorRepository.save(jogador1);
        jogadorRepository.save(jogador2);

        return partidaMapper.toDto(partidaRepository.save(partida));
    }

    public PartidaResponseDto placarCartoesAmarelos(Long id, int cartoesJogador1, int cartoesJogador2) {
        Partida partida = partidaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partida inexistente"));

        Jogador jogador1 = jogadorRepository.findById(partida.getJogador1().getId()).orElseThrow(() -> new EntityNotFoundException("Jogador inexistente"));
        Jogador jogador2 = jogadorRepository.findById(partida.getJogador2().getId()).orElseThrow(() -> new EntityNotFoundException("Jogador inexistente"));

        partida.setCartaoAmareloJogador1(cartoesJogador1);
        partida.setCartaoAmareloJogador2(cartoesJogador2);

        jogador1.setCartoesAmarelos(jogador1.getCartoesAmarelos()+cartoesJogador1);
        jogador2.setCartoesAmarelos(jogador2.getCartoesAmarelos()+cartoesJogador2);

        partida.setGolsJogador1(jogador1.getGolsMarcados());
        partida.setGolsJogador2(jogador2.getGolsMarcados());

        jogadorRepository.save(jogador1);
        jogadorRepository.save(jogador2);

        return partidaMapper.toDto(partidaRepository.save(partida));
    }

    public Boolean partidaExistePorId(Long id) {
        return partidaRepository.existsById(id);
    }
}
