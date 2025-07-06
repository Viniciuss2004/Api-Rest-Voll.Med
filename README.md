# Voll Med – API REST de Clínica Médica

Este projeto é uma API REST desenvolvida com **Spring Boot** que simula o sistema de uma clínica médica fictícia chamada **Voll Med**. A aplicação permite o cadastro e gerenciamento de pacientes, médicos e o agendamento de consultas médicas. O sistema também possui autenticação com segurança via tokens, tratamento de erros, validações, paginação e ordenação de dados, além de uma documentação interativa da API.

---

## 🚀 Funcionalidades

### 👨‍⚕️ Médicos
- Cadastro de médicos (CRUD)
- Atualização de informações
- Inativação lógica (exclusão)
- Listagem com paginação e ordenação
- Validações de dados

### 🧑‍💼 Pacientes
- Cadastro de pacientes (CRUD)
- Atualização de informações
- Inativação lógica (exclusão)
- Listagem com paginação e ordenação
- Validações de dados

### 📅 Consultas
- Agendamento de consultas entre médicos e pacientes
- Cancelamento de consultas com motivo
- Regras de negócio para garantir agendamentos válidos (ex: médico disponível, paciente ativo)

### 🔐 Autenticação e Autorização
- Login com autenticação via token JWT
- Proteção de endpoints com base em roles e permissões

### ⚠️ Tratamento de Erros
- Tratamento global de exceções
- Mensagens de erro padronizadas e amigáveis

### 📃 Documentação da API
- Documentação interativa com Swagger (OpenAPI 3)
- Facilita testes e integração de terceiros

### ✅ Testes Automatizados
- Testes de unidade e de integração utilizando **JUnit** e **Mockito**

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security + JWT**
- **Hibernate**
- **Swagger (Springdoc OpenAPI 3)**
- **JUnit 5**
- **Mockito**
- **Banco de dados MySQL** para testes
- **Maven** para gerenciamento de dependências
