package com.agil.gerenciamento_agilstore.controller;

import com.agil.gerenciamento_agilstore.service.ProdutoService;
import com.agil.gerenciamento_agilstore.repository.ProdutoRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CLIControllerTest {

    private CLIController cliController;

    @Before
    public void setup() {
        ProdutoService produtoService = new ProdutoService(new ProdutoRepositoryImpl());
        cliController = new CLIController(produtoService);
    }

    @Test
    public void testIniciar() {
        assertNotNull(cliController);
    }
}
