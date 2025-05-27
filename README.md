# 💲Minhas Finanças – API REST com Java e Spring Boot

API REST em Java com Spring Boot para gerenciamento de receitas, despesas e saldo de usuários. Cada usuário possui uma carteira vinculada, permitindo controle financeiro completo e organizado.

---

##  Descrição

Este projeto foi desenvolvido com foco em aprimorar habilidades backend com Java, Spring Boot e banco de dados relacional (PostgreSQL). A aplicação permite o gerenciamento financeiro pessoal, com funcionalidades como:

- ✔️ Criação de usuários com carteira automática  
- ✔️ Cadastro e edição de receitas e despesas  
- ✔️ Cálculo do saldo atual do usuário  
- 🚧 Filtragem por tipo, descrição ou período *(em desenvolvimento)*  
- 🔒 Autenticação com JWT *(em desenvolvimento)*  
- ✔️ Boas práticas com DTOs, validações e arquitetura em camadas  


O objetivo principal é aplicar conceitos sólidos de desenvolvimento backend e criar uma API pronta para evoluir com frontend e deploy em nuvem.

---

##  Funcionalidades Concluídas

- ✔️ Cadastro de usuários com carteira vinculada
- ✔️ Cadastro, edição e exclusão de carteiras, receitas e despesas
- ✔️ Cálculo automático do saldo

##  Funcionalidades em Desenvolvimento

- 🔒 Autenticação com JWT
- 🔍 Filtros por tipo, data e descrição
- 📄 Documentação da API com Swagger

---

## Tecnologias Utilizadas

- **Java 17** – Linguagem principal do projeto
- **Spring Boot** – Framework para facilitar a criação da API REST
- **Spring Data JPA** – Abstração para acesso ao banco de dados
- **PostgreSQL** – Banco de dados relacional usado na aplicação
- **Hibernate Validator** – Validações automáticas com anotações como @NotNull
- **Jakarta Validation API** – Padrão para validação de dados
- **Maven** – Gerenciador de dependências e build
- **Postman** – Ferramenta para testes manuais da API

---

## 📦 Como Executar o Projeto

### Pré-requisitos

- Java 17 instalado
- PostgreSQL rodando localmente
- IDE como IntelliJ ou Eclipse (ou terminal com Maven)

### Passos para rodar localmente

```bash
# Clone o repositório
git clone https://github.com/jaquelineon/minhas-financas.git

# Acesse o diretório do projeto
cd minhas-financas

# Configure o application.properties com seus dados do PostgreSQL

# Execute o projeto via sua IDE ou com o comando:
./mvnw spring-boot:run
```

## 📩 Contato

Feito por [Jaqueline Viana](https://github.com/jaquelineon)

Entre em contato: jaquelineeviana@gmail.com