
# **Projeto de Gerenciamento de Produtos da AgilStore**

Este projeto é uma aplicação desenvolvida em Java 21, com foco em uma interface de terminal (CLI) e suporte a API REST simples para gerenciamento de produtos da loja fictícia AgilStore. A aplicação permite operações como cadastro, listagem, atualização, exclusão e busca de produtos no inventário, seguindo os princípios de Orientação a Objetos e boas práticas de desenvolvimento.

---

## **Tecnologias Utilizadas**

- Java 21
- Spring Boot
- Maven  
- Lombok  
- Jackson  

---

## **Pré-requisitos**

Para executar este projeto, é necessário ter instalado:  

- JDK 21 ou superior  
- IDE de sua preferência
- Maven versão 3.9.9 ou superior

---

## **Como Executar o Projeto Localmente**

Siga as instruções abaixo para executar o projeto localmente:

1. Clone este repositório para a sua máquina local.  
2. Abra a pasta do projeto na sua IDE de preferência.  
3. Rode o projeto a partir da classe `GerenciamentoAgilstoreApplication`.  

---

## **Funcionalidades da Aplicação**

A aplicação oferece as seguintes funcionalidades por meio de uma interface de terminal:

- **Cadastrar um produto:**  
  Permite que os usuários adicionem um novo produto ao inventário. Cada produto recebe um ID sequencial único e possui os atributos: nome, categoria, quantidade em estoque e preço.  

- **Listar produtos:**  
  Exibe todos os produtos cadastrados no inventário. Inclui opções de filtro por categoria ou ordenação por nome, quantidade ou preço.  

- **Atualizar informações de um produto:**  
  Permite modificar os dados de um produto existente no inventário, identificado pelo seu ID. O usuário pode atualizar um ou mais atributos, como nome, categoria, quantidade ou preço, com validação dos dados inseridos.  

- **Excluir um produto:**  
  Remove um produto do inventário com base no ID. Inclui uma confirmação antes da exclusão.  

- **Buscar produto:**  
  Permite buscar e exibir os detalhes de um produto específico pelo ID ou por parte do nome. Caso nenhum produto seja encontrado, a aplicação exibe uma mensagem apropriada.  

- **Persistência de dados:**  
  Salva automaticamente o inventário em um arquivo JSON ao encerrar a aplicação, garantindo que os dados sejam mantidos entre as execuções.  
