package com.stcakyforge.matchpoint.dtos.response;

public record PartidaResponseDto(
        Long idPartida,
        Long idJogador1,
        Long idJogador2,
        int golsJogador1,
        int golsJogador2,
        int cartaoAmareloJogador1,
        int cartaoAmareloJogador2
){}