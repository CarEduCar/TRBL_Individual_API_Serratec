# 🎓 API de Gestão Escolar - TRBL Individual (Serratec)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

## 📖 Descrição do Projeto e Tema
Este projeto é o Trabalho Individual desenvolvido para o programa de Residência em TIC/Software do **Serratec**. 

O tema escolhido é um **Sistema de Gestão Escolar**, desenhado para centralizar e organizar o ambiente acadêmico. A API RESTful gerencia o ecossistema educacional completo, lidando com o cadastro de **Alunos** (e seus respectivos Perfis Sociais), **Professores**, **Cursos** e **Turmas**, incluindo os relacionamentos complexos entre essas entidades.

---

## 🗂️ Arquitetura e Estrutura de Pacotes

Para garantir a escalabilidade e a manutenção do código, o projeto foi rigorosamente estruturado em camadas de responsabilidade única:

* **`Config`**: Contém as configurações globais do projeto. Aqui reside a classe `SwaggerConfig`, responsável por facilitar a documentação da API.
* **`Controller`**: Principal entrada da API. Armazena os controllers que recebem as requisições HTTP, gerenciam as rotas e abrigam as anotações do Swagger.
* **`Domain`**: O núcleo do banco de dados. Contém as entidades JPA (ex: `Aluno`, `PerfilSocial`, `Turma`) que espelham as tabelas do banco, utilizando as regras estritas do `jakarta.persistence` (ex: `@Column(nullable = false)` e mapeamentos `@OneToOne`, `@ManyToMany`).
* **`Exception`**: Camada de tratamento de erros. Possui exceções customizadas, `ValorNaoEncontradoException` por exemplo, e um manipulador global que recebe e executa as falhas.
* **`Repository`**: Interfaces que estendem o `JpaRepository`. Responsáveis pela comunicação direta com o banco de dados.
* **`RequestDTO`**: Objetos de Transferência de Dados focados na **entrada**. Blindam a aplicação validando os JSONs recebidos.
* **`ResponseDTO`**: Objetos focados na **saída**. Filtram os dados sensíveis das entidadesa na hora de devolver a resposta HTTP ao usuário.
* **`Service`**: Contém toda a lógica de negócios, o processamento de regras, validações de relacionamentos e a conversão entre DTOs e Entidades.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL
* **Mapeamento Objeto-Relacional:** Hibernate / Spring Data JPA
* **Documentação Visual:** Springdoc OpenAPI (Swagger UI)
* **Validação:** Jakarta Bean Validation
* **Gerenciamento de Dependências:** Maven

---

## ⚙️ Instruções de Execução

### Pré-requisitos
Para rodar este projeto localmente, você precisará ter instalado em sua máquina:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
* Uma IDE (Eclipse, IntelliJ IDEA ou VS Code).
* PostgreSQL configurado e rodando localmente na porta `5432` (Pode ser necessário a alteração dependendo da sua configuração).
* Postman (também é possível utilizar o Swagger).

### Como Rodar
1. Clone este repositório:
   ```bash
   git clone [https://github.com/SeuUsuario/TRBL_Individual_API.git](https://github.com/SeuUsuario/TRBL_Individual_API.git)
