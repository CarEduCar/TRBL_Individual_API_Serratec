# 🎓 API de Gestão Escolar (TRBL Individual - Serratec)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

> API RESTful desenvolvida como Trabalho Individual para o programa de Residência em Software do Serratec. O sistema realiza o gerenciamento completo de uma instituição de ensino, controlando Alunos, Perfis Sociais, Turmas, Cursos e Professores.

## 📋 Funcionalidades

O sistema foi desenhado com arquitetura em camadas (Controller, Service, Repository) e implementa as seguintes funcionalidades:

- **CRUD Completo:** Criação, leitura, atualização e exclusão de recursos acadêmicos.
- **Relacionamentos Complexos:** Implementação de relacionamentos `@OneToOne` (com `@MapsId` entre Aluno e Perfil Social), `@OneToMany` e `@ManyToMany` (Alunos e Turmas).
- **Tratamento Global de Exceções:** Classe `@ControllerAdvice` padronizando respostas de erro (Ex: `404 Not Found`, `400 Bad Request`, `409 Conflict`) no formato JSON amigável, capturando erros de conversão de URL e validação.
- **Validação de Dados:** Uso rigoroso do `jakarta.validation` (`@NotNull`, `@NotBlank`, `@Size`) em classes DTO para garantir a integridade dos dados na entrada.
- **Data Transfer Objects (DTO):** Isolamento entre as entidades do banco de dados e a saída da API, protegendo dados sensíveis e evitando recursão infinita no JSON.
- **Consultas Otimizadas:** Uso de `JOIN FETCH` no repositório para mitigar o problema de consultas N+1 na listagem de dados vinculados.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Framework:** Spring Boot 3
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL (ou MySQL)
- **Documentação:** Springdoc OpenAPI (Swagger UI)
- **Gerenciamento de Dependências:** Maven

## 🚀 Como Executar o Projeto

Siga os passos abaixo para rodar a API na sua máquina local.

### Pré-requisitos
- [JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) instalado.
- IDE de sua preferência (Eclipse, IntelliJ IDEA, etc).
- Banco de dados relacional configurado e rodando.

### Passos
1. Faça o clone do repositório:
   ```bash
   git clone [https://github.com/SeuUsuario/TRBL_Individual_API.git](https://github.com/SeuUsuario/TRBL_Individual_API.git)
