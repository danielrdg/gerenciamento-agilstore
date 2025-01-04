package com.agil.gerenciamento_agilstore;

import com.agil.gerenciamento_agilstore.controller.CLIController;
import com.agil.gerenciamento_agilstore.repository.ProdutoRepositoryImpl;
import com.agil.gerenciamento_agilstore.service.ProdutoService;

public class GerenciamentoAgilstoreApplication {
	public static void main(String[] args) {
		ProdutoRepositoryImpl repository = new ProdutoRepositoryImpl();
		ProdutoService service = new ProdutoService(repository);
		CLIController cliController = new CLIController(service);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			String caminhoArquivo = "produtos.json";
			repository.salvarEmArquivo(caminhoArquivo);
			System.out.println("\nDados salvos automaticamente em: " + caminhoArquivo);
		}));
		cliController.iniciar();
	}
}
