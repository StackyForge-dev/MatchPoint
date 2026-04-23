package com.stcakyforge.matchpoint.service;

import com.stcakyforge.matchpoint.dtos.request.JogadorRequestDto;
import com.stcakyforge.matchpoint.dtos.response.JogadorResponseDto;
import com.stcakyforge.matchpoint.mapper.JogadorMapper;
import com.stcakyforge.matchpoint.model.Campeonato;
import com.stcakyforge.matchpoint.model.Jogador;
import com.stcakyforge.matchpoint.repository.CampeonatoRepository;
import com.stcakyforge.matchpoint.repository.JogadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final JogadorMapper mapper;

    public JogadorService(JogadorRepository jogadorRepository, JogadorMapper mapper, CampeonatoRepository campeonatoRepository) {
        this.jogadorRepository = jogadorRepository;
        this.mapper = mapper;
        this.campeonatoRepository = campeonatoRepository;
    }

    public JogadorResponseDto criarJogador(JogadorRequestDto request) {
        Jogador player = new Jogador();

        Campeonato camp = campeonatoRepository.findById(request.idCampeonato()).orElseThrow(EntityNotFoundException::new);
                player.setNome(request.nome());
                player.setTime(request.time());
                player.setCampeonato(camp);

        camp.getJogadores().add(player);

        campeonatoRepository.save(camp);
        return mapper.toDto(jogadorRepository.save(player));
    }

    public List<JogadorResponseDto> pegarTodosJogadores() {
        return mapper.toDto(jogadorRepository.findAll());
    }

    public JogadorResponseDto pegarJogadorPorId(Long id) {
        return mapper.toDto(jogadorRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    
    public int saldoGols(Long id) {
        Jogador jogador = jogadorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        int saldoGols = jogador.getGolsMarcados() - jogador.getGolsSofridos();

        jogador.setSaldoGols(saldoGols);
        jogadorRepository.save(jogador);

        return jogador.getSaldoGols();
    }

    public List<JogadorResponseDto> pegarJogadoresPorCampeopnato(Long idCampeonato) {
        if (campeonatoRepository.findById(idCampeonato).isPresent()) {
            Campeonato campeonato = campeonatoRepository.findById(idCampeonato).orElseThrow(EntityNotFoundException::new);

            return mapper.toDto(campeonato.getJogadores());
        }
        return null;
    }

    public void deletarJogador(Long id){
        if(jogadorRepository.findById(id).isPresent()){
            jogadorRepository.deleteById(id);
        }
    }

}
