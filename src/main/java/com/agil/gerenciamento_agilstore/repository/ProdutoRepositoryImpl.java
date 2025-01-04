package com.agil.gerenciamento_agilstore.repository;

import com.agil.gerenciamento_agilstore.model.Produto;

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
}
