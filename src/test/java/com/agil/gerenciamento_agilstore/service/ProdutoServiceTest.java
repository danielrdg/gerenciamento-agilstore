package com.agil.gerenciamento_agilstore.service;

import com.agil.gerenciamento_agilstore.model.Produto;
import com.agil.gerenciamento_agilstore.repository.ProdutoRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoServiceTest {

    private ProdutoService produtoService;

    @Before
    public void setup() {
        produtoService = new ProdutoService(new ProdutoRepositoryImpl());
    }

    @Test
    public void testAdicionarProduto() {
        Produto produto = new Produto("Notebook", "Eletrônicos", 5, 3500.0);
        produtoService.adicionarProduto(produto);

        List<Produto> produtos = produtoService.listarProdutos();
        assertEquals(1, produtos.size());
        assertEquals("Notebook", produtos.get(0).getNome());
    }

    @Test(expected = RuntimeException.class)
    public void testBuscarProdutoPorIdInexistente() {
        produtoService.buscarProdutoPorId("999");
    }


    @Test
    public void testValidarDadosProduto() {
        Produto produto = new Produto("", "Eletrônicos", 5, 3500.0);
        boolean valido = produtoService.validarDadosProduto(produto);
        assertFalse(valido);
    }
}
