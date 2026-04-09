package com.stcakyforge.matchpoint.dtos.request;

public record PartidaRequestDto (

        Long idJogador1,
        Long idJogador2,
        int golsJogador1,
        int golsJogador2,
        int cartaoAmareloJogador1,
        int cartaoAmareloJogador2
){}