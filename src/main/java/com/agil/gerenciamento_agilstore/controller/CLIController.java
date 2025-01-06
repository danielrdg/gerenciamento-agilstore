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
            System.out.println("5. Buscar Produto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> adicionarProduto(teclado);
                case 2 -> listarProdutos(teclado);
                case 3 -> atualizarProduto(teclado);
                case 4 -> removerProduto(teclado);
                case 5 -> buscarProduto(teclado);
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

    private void buscarProduto(Scanner teclado) {
        System.out.println("Buscar Produto:");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nome");
        System.out.print("Escolha uma opção: ");
        int opcao = teclado.nextInt();
        teclado.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Digite o ID do produto: ");
                String id = teclado.nextLine();
                try {
                    Produto produto = produtoService.buscarProdutoPorId(id);
                    System.out.println("Produto encontrado: " + produto);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                System.out.print("Digite parte do nome do produto: ");
                String nomeParcial = teclado.nextLine();
                List<Produto> produtos = produtoService.buscarPorNome(nomeParcial);
                if (produtos.isEmpty()) {
                    System.out.println("Nenhum produto encontrado com o nome: " + nomeParcial);
                } else {
                    System.out.println("Produtos encontrados:");
                    produtos.forEach(System.out::println);
                }
            }
            default -> System.out.println("Opção inválida!");
        }
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
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    if (novoNome == null || novoNome.trim().isEmpty()) {
                        System.out.println("O nome do produto não pode ser vazio.");
                    } else {
                        produto.setNome(novoNome);
                    }
                }
                case 2 -> {
                    System.out.print("Nova Categoria: ");
                    String novaCategoria = scanner.nextLine();
                    if (novaCategoria == null || novaCategoria.trim().isEmpty()) {
                        System.out.println("A categoria do produto não pode ser vazia.");
                    } else {
                        produto.setCategoria(novaCategoria);
                    }
                }
                case 3 -> {
                    System.out.print("Nova Quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine();
                    if (novaQuantidade < 0) {
                        System.out.println("A quantidade do produto não pode ser negativa.");
                    } else {
                        produto.setQuantidade(novaQuantidade);
                    }
                }
                case 4 -> {
                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine();
                    if (novoPreco < 0) {
                        System.out.println("O preço do produto não pode ser negativo.");
                    } else {
                        produto.setPreco(novoPreco);
                    }
                }
                case 0 -> {
                    if (produtoService.validarDadosProduto(produto)) {
                        produtoService.atualizarProduto(produto);
                        System.out.println("Produto atualizado com sucesso!");
                    } else {
                        System.out.println("Os dados do produto são inválidos. Atualização cancelada.");
                    }
                    return;
                }
                default -> System.out.println("Opção inválida! Escolha novamente.");
            }
        }
    }



    private void listarTodos() {
        List<Produto> produtos = produtoService.listarProdutos();
        produtos.forEach(System.out::println);
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

        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("Detalhes do Produto:");
        System.out.println(produto);
        System.out.print("Tem certeza de que deseja excluir este produto? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            produtoService.removerProduto(id);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

}
