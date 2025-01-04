package com.agil.gerenciamento_agilstore.service;

import com.agil.gerenciamento_agilstore.model.Produto;
import com.agil.gerenciamento_agilstore.repository.ProdutoRepository;

import java.util.Comparator;
import java.util.List;

public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto adicionarProduto(Produto produto) {
        return repository.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = repository.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        }
        return produtos;
    }


    public List<Produto> filtrarPorCategoria(String categoria) {
        return repository.listarTodos().stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public List<Produto> ordenarPorNome() {
        return repository.listarTodos().stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .toList();
    }

    public List<Produto> ordenarPorQuantidade() {
        return repository.listarTodos().stream()
                .sorted(Comparator.comparingInt(Produto::getQuantidade))
                .toList();
    }

    public List<Produto> ordenarPorPreco() {
        return repository.listarTodos().stream()
                .sorted(Comparator.comparingDouble(Produto::getPreco))
                .toList();
    }

    public Produto buscarProdutoPorId(String id) {
        return repository.buscarPorId(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void atualizarProduto(Produto produto) {
        repository.atualizar(produto);
    }

    public void removerProduto(String id) {
        repository.remover(id);
    }

    public boolean validarDadosProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            System.out.println("O nome do produto não pode ser vazio.");
            return false;
        }
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            System.out.println("A categoria do produto não pode ser vazia.");
            return false;
        }
        if (produto.getQuantidade() < 0) {
            System.out.println("A quantidade do produto não pode ser negativa.");
            return false;
        }
        if (produto.getPreco() < 0) {
            System.out.println("O preço do produto não pode ser negativo.");
            return false;
        }
        return true;
    }

    public void salvarEmArquivo(String caminhoArquivo) {
        repository.salvarEmArquivo(caminhoArquivo);
    }
}
