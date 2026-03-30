package com.stcakyforge.matchpoint.dtos.response;

import com.stcakyforge.matchpoint.model.Campeonato;

import java.util.List;

public record PegarPartidasDto(
        List<Campeonato> listaPartidaporCampeonato
) {}
