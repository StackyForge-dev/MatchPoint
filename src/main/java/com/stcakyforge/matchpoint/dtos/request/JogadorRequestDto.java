package com.stcakyforge.matchpoint.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record JogadorRequestDto(

        @NotEmpty(message = "O nome do jogador não pode ser vazio")
        @NotBlank(message = "O nome do jogador não pode estar em branco")
        @NotNull(message = "O nome do jogador não pode ser nulo")
        String name,

        @NotEmpty(message = "O nome do time não pode ser branco")
        @NotBlank(message = "O nome do time não pode estar em branco")
        @NotNull(message = "O nome do time não pode ser nulo")
        String time,

        @NotEmpty(message = "O id do campeonato não pode ser branco")
        @NotBlank(message = "O id do campeonato não pode estar em branco")
        @NotNull(message = "O id do campeonato não pode ser nulo")
        Long Idcampeonato
) {}
