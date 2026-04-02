package com.stcakyforge.matchpoint.dtos.response;

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

        Long partidasComoJogador1,

        Long partidasComoJogador2,

        Long partidasIds,

        int partidasTotais
) {}
