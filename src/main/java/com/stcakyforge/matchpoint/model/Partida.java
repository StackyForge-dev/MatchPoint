package com.stcakyforge.matchpoint.model;

import jakarta.persistence.*;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogador_1_id")
    private Jogador jogador1;

    @ManyToOne
    @JoinColumn(name = "jogador_2_id")
    private Jogador jogador2;

    private int golsJogador1;

    private int golsJogador2;

    private int cartaoAmareloJogador1;

    private int cartaoAmareloJogador2;

    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;

    public Partida() {
    }

    public Partida(Long id, Jogador jogador1, Jogador jogador2, int golsJogador1, int golsJogador2, int cartaoAmareloJogador1, int cartaoAmareloJogador2) {
        this.id = id;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.golsJogador1 = 0;
        this.golsJogador2 = 0;
        this.cartaoAmareloJogador1 = 0;
        this.cartaoAmareloJogador2 = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public int getGolsJogador1() {
        return golsJogador1;
    }

    public void setGolsJogador1(int golsJogador1) {
        this.golsJogador1 = golsJogador1;
    }

    public int getGolsJogador2() {
        return golsJogador2;
    }

    public void setGolsJogador2(int golsJogador2) {
        this.golsJogador2 = golsJogador2;
    }

    public int getCartaoAmareloJogador1() {
        return cartaoAmareloJogador1;
    }

    public void setCartaoAmareloJogador1(int cartaoAmareloJogador1) {
        this.cartaoAmareloJogador1 = cartaoAmareloJogador1;
    }

    public int getCartaoAmareloJogador2() {
        return cartaoAmareloJogador2;
    }

    public void setCartaoAmareloJogador2(int cartaoAmareloJogador2) {
        this.cartaoAmareloJogador2 = cartaoAmareloJogador2;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
}