package com.stcakyforge.matchpoint.dtos.response;

import java.util.List;

import com.stcakyforge.matchpoint.model.Campeonato;
import com.stcakyforge.matchpoint.model.Partida;

public record JogadorResponseDto(
        Long id,

        String nome,

        int pontos,

        int partidasJogadas,

        int vitorias,

        int empate,

        int derrota,

        int golsMarcados,

        int golsSofridos,

        int saldoGols,

        String time,

        Long idCampeonato,

        List<Partida> partidasComoJogador1,

        List<Partida> partidasComoJogador2,

        int partidasTotais
) {}
