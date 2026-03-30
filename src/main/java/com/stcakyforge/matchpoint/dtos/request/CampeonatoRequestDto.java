package com.stcakyforge.matchpoint.dtos.request;

import com.stcakyforge.matchpoint.enums.CampTypes;
import com.stcakyforge.matchpoint.model.Jogador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

public record CampeonatoRequestDto(

        @NotEmpty(message = "O nome do campeonato não pode estar vazio")
        @NotBlank(message = "O nome do campeonato não pode estar em branco")
        @NotNull(message = "O nome do campeonato não pode ser nulo")
        String nomeCampeonato,

        List<Jogador> jogadores,

        @NotEmpty(message = "A data de início do campeonato não pode estar vazio")
        @NotBlank(message = "A data de início campeonato não pode estar em branco")
        @NotNull(message = "A data de início campeonato não pode ser nulo")
        @DateTimeFormat(iso = DATE, pattern = "yyyy-MM-dd")
        String dataInicio,

        @NotEmpty(message = "A data de término do campeonato não pode estar vazio")
        @NotBlank(message = "A data do término não pode estar em branco")
        @NotNull(message = "A data do término não pode ser nulo")
        @DateTimeFormat(iso = DATE, pattern = "yyyy-MM-dd")
        String dataFim,

        @NotEmpty(message = "O estilo de pontuação do campeonato não pode estar vazio")
        @NotBlank(message = "O estilo de pontuação não pode estar em branco")
        @NotNull(message = "O estilo de pontuação não pode ser nulo")
        CampTypes estiloPontuacao
) {}