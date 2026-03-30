package com.stcakyforge.matchpoint.service;

import com.stcakyforge.matchpoint.dtos.request.CampeonatoRequestDto;
import com.stcakyforge.matchpoint.dtos.response.CampeonatoResponseDto;
import com.stcakyforge.matchpoint.dtos.response.JogadorResponseDto;
import com.stcakyforge.matchpoint.mapper.CampeonatoMapper;
import com.stcakyforge.matchpoint.mapper.JogadorMapper;
import com.stcakyforge.matchpoint.model.Campeonato;
import com.stcakyforge.matchpoint.model.Jogador;
import com.stcakyforge.matchpoint.repository.CampeonatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final CampeonatoMapper mapper;
    private final JogadorMapper jogadorMapper;

    public CampeonatoService(CampeonatoRepository campeonatoRepository, CampeonatoMapper mapper, JogadorMapper jogadorMapper) {
        this.campeonatoRepository = campeonatoRepository;
        this.mapper = mapper;
        this.jogadorMapper = jogadorMapper;
    }

    public CampeonatoResponseDto criarCampeonato(CampeonatoRequestDto campeonatoRequestDto) {
        return mapper.toDto(campeonatoRepository.save(mapper.toEntity(campeonatoRequestDto)));
    }

    public List<CampeonatoResponseDto> listarCampeopnatos() {
        return mapper.toDto(campeonatoRepository.findAll());
    }

    public List<JogadorResponseDto> rankingJogadoresPorCampeonato(Long campeonatoId) {

        Campeonato campeonato = campeonatoRepository.findById(campeonatoId).orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado"));

        List<Jogador> ranking = campeonato.getJogadores().stream()
                .sorted(Comparator.comparingInt(Jogador::getPontos).reversed())
                .toList();

        return jogadorMapper.toDto(ranking);
    }

    public List<JogadorResponseDto> rankingJogadoresGeral() {

        List<Jogador> ranking = campeonatoRepository.findAll()
                .stream().flatMap(c -> c.getJogadores().stream())
                .sorted(Comparator.comparingInt(Jogador::getPontos).reversed())
                .toList();

        return jogadorMapper.toDto(ranking);
    }
    public void deletarCampeonato(Long id) {
        campeonatoRepository.deleteById(id);
    }

}