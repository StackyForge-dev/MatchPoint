package com.stcakyforge.matchpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stcakyforge.matchpoint.model.Partida;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByCampeonatoId(Long campeonato_id);
}
