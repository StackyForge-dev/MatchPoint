package com.stcakyforge.matchpoint.mapper;

import com.stcakyforge.matchpoint.dtos.response.PegarPartidasDto;
import com.stcakyforge.matchpoint.model.Partida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PegarPartidasMapper {
    PegarPartidasDto toDto(Partida partida);

    List<PegarPartidasDto> toDto (List<Partida> partida);
}
