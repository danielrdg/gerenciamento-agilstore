
# **Projeto de Gerenciamento de Produtos da AgilStore**

Este projeto é uma aplicação desenvolvida em Java 21, com foco em uma interface de terminal (CLI) e suporte a API REST simples para gerenciamento de produtos da loja fictícia AgilStore. A aplicação permite operações como cadastro, listagem, atualização, exclusão e busca de produtos no inventário, seguindo os princípios de Orientação a Objetos e boas práticas de desenvolvimento.

---

## **Tecnologias Utilizadas**

- Java 21
- Spring Boot
- Maven  
- JUnit  
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

---
## **Decisões de Design**

O projeto foi desenvolvido com foco em simplicidade, organização e funcionalidade, resultando nas seguintes decisões principais:

- Camadas Modulares: Separação clara em camadas (Model, Repository, Service, Controller) para facilitar manutenção e escalabilidade.
- Substituição do Lombok: Devido a problemas de configuração da IDE, métodos get, set, toString e o construtor foram gerados manualmente, eliminando a dependência do Lombok.
- Persistência Simples: Os dados são salvos automaticamente em um arquivo JSON ao encerrar a aplicação, garantindo persistência sem dependência de banco de dados.
- Validações Robustas: Garantia de consistência nos dados do inventário com validações implementadas na camada de serviço.
- Interface CLI: Menus organizados e instruções claras para facilitar o uso e a experiência do usuário.
- Testes Unitários:
  - Testes criados com JUnit, abrangendo as camadas de serviço e repositório.
  - Verificam funcionalidades como adição, remoção, listagem e validação de produtos.
  - Garantem o funcionamento correto das operações principais e aumentam a confiabilidade do projeto.
