package com.agil.gerenciamento_agilstore.controller;

import com.agil.gerenciamento_agilstore.model.Produto;
import com.agil.gerenciamento_agilstore.service.ProdutoService;

import java.util.Scanner;

public class CLIController {
    private final ProdutoService produtoService;

    public CLIController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public void iniciar() {
        Scanner teclado = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> adicionarProduto(teclado);
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto(teclado);
                case 4 -> removerProduto(teclado);
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void adicionarProduto(Scanner teclado) {
        System.out.println("\nAdicionando Produto:");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Categoria: ");
        String categoria = teclado.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Preço: ");
        double preco = teclado.nextDouble();
        teclado.nextLine();

        Produto produto = new Produto(nome, categoria, quantidade, preco);
        produtoService.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    private void listarProdutos() {
        System.out.println("\nLista de Produtos:");
        produtoService.listarProdutos().forEach(System.out::println);
    }

    private void atualizarProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        String id = scanner.nextLine();

        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("Novo Nome (atual: " + produto.getNome() + "): ");
        String novoNome = scanner.nextLine();
        produto.setNome(novoNome.isEmpty() ? produto.getNome() : novoNome);

        produtoService.atualizarProduto(produto);
        System.out.println("Produto atualizado!");
    }

    private void removerProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        String id = scanner.nextLine();

        produtoService.removerProduto(id);
        System.out.println("Produto removido!");
    }
}
