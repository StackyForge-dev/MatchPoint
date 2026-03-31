package com.stcakyforge.matchpoint.mapper;

import com.stcakyforge.matchpoint.dtos.request.CampeonatoRequestDto;
import com.stcakyforge.matchpoint.dtos.response.CampeonatoResponseDto;
import com.stcakyforge.matchpoint.model.Campeonato;
import com.stcakyforge.matchpoint.model.Jogador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CampeonatoMapper {

    @Mapping(source = "jogadores", target = "totalJogadores", qualifiedByName = "countJogadores")
    @Mapping(source = "jogadores", target = "idsJogadores", qualifiedByName = "extractJogadorIds")
    CampeonatoResponseDto toDto(Campeonato campeonato);

    List<CampeonatoResponseDto> toDto (List<Campeonato> campeonatos);

    Campeonato toEntity(CampeonatoRequestDto campeonatoRequest);

    @Named("countJogadores")
    default int countJogadores(List<Jogador> jogadores) {
        return jogadores != null ? jogadores.size() : 0;
    }

    @Named("extractJogadorIds")
    default List<Long> extractJogadorIds(List<Jogador> jogadores) {
        if (jogadores == null || jogadores.isEmpty()) {
            return List.of();
        }
        return jogadores.stream()
                .map(Jogador::getId)
                .collect(Collectors.toList());
    }
}