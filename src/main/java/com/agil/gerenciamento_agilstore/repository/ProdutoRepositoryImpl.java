package com.agil.gerenciamento_agilstore.repository;

import com.agil.gerenciamento_agilstore.model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();

    @Override
    public Produto salvar(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public List<Produto> listarTodos() {
        return produtos;
    }


    @Override
    public Optional<Produto> buscarPorId(String id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public void remover(String id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public void atualizar(Produto produto) {
        buscarPorId(produto.getId()).ifPresent(p -> {
            p.setNome(produto.getNome());
            p.setCategoria(produto.getCategoria());
            p.setQuantidade(produto.getQuantidade());
            p.setPreco(produto.getPreco());
        });
    }

    @Override
    public void salvarEmArquivo(String caminhoArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(caminhoArquivo), produtos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    @Override
    public List<Produto> buscarPorNome(String nomeParcial) {
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(nomeParcial.toLowerCase()))
                .toList();
    }



}
