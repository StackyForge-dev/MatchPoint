package com.stcakyforge.matchpoint.dtos.response;

import com.stcakyforge.matchpoint.enums.CampTypes;
import com.stcakyforge.matchpoint.model.Jogador;

import java.time.LocalDate;
import java.util.List;

public record CampeonatoResponseDto(
        Long id,
        String nomeCampeonato,
        List<Long> idsJogadores,
        int totalJogadores,
        LocalDate dataInicio,
        LocalDate dataFim,
        CampTypes estiloPontuacao
) {}