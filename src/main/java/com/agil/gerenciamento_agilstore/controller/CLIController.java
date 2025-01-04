package com.agil.gerenciamento_agilstore.controller;

import com.agil.gerenciamento_agilstore.model.Produto;
import com.agil.gerenciamento_agilstore.service.ProdutoService;

import java.util.List;
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
                case 2 -> listarProdutos(teclado);
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

    private void listarProdutos(Scanner teclado) {
        System.out.println("\nLista de Produtos:");
        System.out.println("1. Listar todos");
        System.out.println("2. Filtrar por categoria");
        System.out.println("3. Ordenar por nome");
        System.out.println("4. Ordenar por quantidade");
        System.out.println("5. Ordenar por preço");
        System.out.print("Escolha uma opção: ");
        int opcao = teclado.nextInt();
        teclado.nextLine();

        switch (opcao) {
            case 1 -> listarTodos();
            case 2 -> filtrarPorCategoria(teclado);
            case 3 -> ordenarPorNome();
            case 4 -> ordenarPorQuantidade();
            case 5 -> ordenarPorPreco();
            default -> System.out.println("Opção inválida!");
        }
    }


    private void atualizarProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        String id = scanner.nextLine();

        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        while (true) {
            System.out.println("\nEscolha o atributo que deseja atualizar:");
            System.out.println("1. Nome (atual: " + produto.getNome() + ")");
            System.out.println("2. Categoria (atual: " + produto.getCategoria() + ")");
            System.out.println("3. Quantidade (atual: " + produto.getQuantidade() + ")");
            System.out.println("4. Preço (atual: " + produto.getPreco() + ")");
            System.out.println("0. Concluir atualização");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    produto.setNome(novoNome.isEmpty() ? produto.getNome() : novoNome);
                }
                case 2 -> {
                    System.out.print("Nova Categoria: ");
                    String novaCategoria = scanner.nextLine();
                    produto.setCategoria(novaCategoria.isEmpty() ? produto.getCategoria() : novaCategoria);
                }
                case 3 -> {
                    System.out.print("Nova Quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    produto.setQuantidade(novaQuantidade);
                }
                case 4 -> {
                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine(); // Consome a quebra de linha
                    produto.setPreco(novoPreco);
                }
                case 0 -> {
                    produtoService.atualizarProduto(produto);
                    System.out.println("Produto atualizado com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida! Escolha novamente.");
            }
        }
    }

    private void listarTodos() {
        List<Produto> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void filtrarPorCategoria(Scanner teclado) {
        System.out.print("Digite a categoria para filtrar: ");
        String categoria = teclado.nextLine();
        List<Produto> produtosFiltrados = produtoService.filtrarPorCategoria(categoria);
        if (produtosFiltrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado na categoria: " + categoria);
        } else {
            produtosFiltrados.forEach(System.out::println);
        }
    }

    private void ordenarPorNome() {
        List<Produto> produtosOrdenados = produtoService.ordenarPorNome();
        produtosOrdenados.forEach(System.out::println);
    }

    private void ordenarPorQuantidade() {
        List<Produto> produtosOrdenados = produtoService.ordenarPorQuantidade();
        produtosOrdenados.forEach(System.out::println);
    }

    private void ordenarPorPreco() {
        List<Produto> produtosOrdenados = produtoService.ordenarPorPreco();
        produtosOrdenados.forEach(System.out::println);
    }



    private void removerProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        String id = scanner.nextLine();

        produtoService.removerProduto(id);
        System.out.println("Produto removido!");
    }
}
