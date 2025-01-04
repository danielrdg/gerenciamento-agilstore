package com.agil.gerenciamento_agilstore.repository;

import com.agil.gerenciamento_agilstore.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Produto salvar(Produto produto);
    List<Produto> listarTodos();
    Optional<Produto> buscarPorId(String id);
    void remover(String id);
    void atualizar(Produto produto);
    void salvarEmArquivo(String caminhoArquivo);
}
