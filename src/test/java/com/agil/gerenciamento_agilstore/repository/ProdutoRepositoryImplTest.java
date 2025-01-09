package com.agil.gerenciamento_agilstore.repository;

import com.agil.gerenciamento_agilstore.model.Produto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoRepositoryImplTest {

    private ProdutoRepositoryImpl repository;

    @Before
    public void setup() {
        repository = new ProdutoRepositoryImpl();
    }

    @Test
    public void testSalvarProduto() {
        Produto produto = new Produto("Smartphone", "Eletrônicos", 10, 1500.0);
        repository.salvar(produto);

        List<Produto> produtos = repository.listarTodos();
        assertEquals(1, produtos.size());
        assertEquals("Smartphone", produtos.get(0).getNome());
    }

    @Test
    public void testRemoverProduto() {
        Produto produto = new Produto("Teclado", "Periféricos", 20, 250.0);
        repository.salvar(produto);
        repository.remover(produto.getId());

        List<Produto> produtos = repository.listarTodos();
        assertTrue(produtos.isEmpty());
    }

    @Test
    public void testListarTodosSemProdutos() {
        List<Produto> produtos = repository.listarTodos();
        assertTrue(produtos.isEmpty());
    }
}
