package com.stcakyforge.matchpoint.dtos.response;

import java.util.List;

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

        int partidasComoJogador1,

        int partidasComoJogador2,

        List<Long> partidasIds,

        int partidasTotais
) {}
