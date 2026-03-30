package com.stcakyforge.matchpoint.model;

import com.stcakyforge.matchpoint.enums.CampTypes;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "campeonato")
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCampeonato;

    private int qtdJogadores;

    private int qtdPartidas;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Enumerated(value = STRING)
    private CampTypes estiloPontuacao;

    @OneToMany(mappedBy = "campeonato")
    private List<Partida> partidas;

    @OneToMany(mappedBy = "campeonato")
    private List<Jogador> jogadores;

    public Campeonato() {
    }

    public Campeonato(Long id, String nomeCampeonato, int qtdJogadores, int qtdPartidas, LocalDate dataInicio, LocalDate dataFim, CampTypes estiloPontuacao, List<Jogador> jogadores, List<Partida> partidas) {
        this.id = id;
        this.nomeCampeonato = nomeCampeonato;
        this.qtdJogadores = qtdJogadores;
        this.qtdPartidas = qtdPartidas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.estiloPontuacao = estiloPontuacao;
        this.partidas = partidas;
        this.jogadores = jogadores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public int getQtdJogadores() {
        return qtdJogadores;
    }

    public void setQtdJogadores(int qtdJogadores) {
        this.qtdJogadores = qtdJogadores;
    }

    public int getQtdPartidas() {
        return qtdPartidas;
    }

    public void setQtdPartidas(int qtdPartidas) {
        this.qtdPartidas = qtdPartidas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public CampTypes getEstiloPontuacao() {
        return estiloPontuacao;
    }

    public void setEstiloPontuacao(CampTypes estiloPontuacao) {
        this.estiloPontuacao = estiloPontuacao;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }
}
