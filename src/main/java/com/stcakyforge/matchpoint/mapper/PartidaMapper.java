package com.stcakyforge.matchpoint.mapper;

import com.stcakyforge.matchpoint.dtos.request.PartidaRequestDto;
import com.stcakyforge.matchpoint.dtos.response.PartidaResponseDto;
import com.stcakyforge.matchpoint.model.Partida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartidaMapper {

    PartidaResponseDto toDto(Partida partida);

    List<PartidaResponseDto> toDto (List<Partida> partidas);

    Partida toEntity(PartidaRequestDto partidaRequest);
}
