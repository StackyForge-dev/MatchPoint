package com.stcakyforge.matchpoint.mapper;

import com.stcakyforge.matchpoint.dtos.request.JogadorRequestDto;
import com.stcakyforge.matchpoint.dtos.response.JogadorResponseDto;
import com.stcakyforge.matchpoint.model.Jogador;
import com.stcakyforge.matchpoint.model.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JogadorMapper {

    @Named("pegarJogador1Id")
    default Long pegarJogador1Id(List<Partida> partida) {
        if (partida == null || partida.isEmpty()) {
            return null;
        }
        return (long) partida.size();
    }

    @Named("pegarJogador2Id")
    default Long pegarJogador2Id(List<Partida> partida) {
        if (partida == null || partida.isEmpty()) {
            return null;
        }
        return (long) partida.size();
    }

    default List<Long> pegarPartidasIds(List<Partida> partida) {
        if (partida == null || partida.isEmpty()) {
            return List.of();
        }
        return partida.stream()
                .map(Partida::getId)
                .collect(Collectors.toList());
    }

    @Mapping(source = "campeonato.id", target = "idCampeonato")
    @Mapping(source = "partidasComoJogador1", target = "partidasComoJogador1", qualifiedByName = "pegarJogador1Id")
    @Mapping(source = "partidasComoJogador2", target = "partidasComoJogador2", qualifiedByName = "pegarJogador2Id")
    @Mapping(source = "partidas", target = "partidasIds", qualifiedByName = "pegarPartidasIds")
    JogadorResponseDto toDto(Jogador jogador);

    List<JogadorResponseDto> toDto(List<Jogador> jogadores);

    Jogador toEntity(JogadorRequestDto jogadorRequest);
}