package com.agil.gerenciamento_agilstore.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Produto {
    private String id;
    private String nome;
    private String categoria;
    private int quantidade;
    private double preco;

    public Produto(String nome, String categoria, int quantidade, double preco) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
