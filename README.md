# 🎓 Sistema de Gestão Acadêmica

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-green.svg)](https://spring.io/projects/spring-security)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)

> API RESTful completa para gerenciamento acadêmico, desenvolvida com Spring Boot e implementando autenticação JWT com Spring Security. Projeto acadêmico da disciplina de **Tecnologia para Back-End Avançado**.

---

## 📋 Sobre o Projeto

O **Sistema de Gestão Acadêmica** é uma API REST robusta para gerenciamento completo de instituições de ensino, permitindo o controle de alunos, professores, disciplinas, turmas, matrículas e notas.

### 🎓 Contexto Acadêmico

Este projeto foi desenvolvido como trabalho da disciplina **Tecnologia para Back-End Avançado**, ministrada pelo professor **Antonio Junio Figueiredo da Mata**, com foco em:

- 🔐 **Spring Security** - Implementação completa de autenticação e autorização
- 🎯 **JWT (JSON Web Tokens)** - Autenticação stateless
- 🏗️ **Arquitetura REST** - Boas práticas e padrões RESTful
- 🗄️ **JPA/Hibernate** - Persistência de dados com ORM
- ✅ **Bean Validation** - Validação robusta de dados
- 🔄 **Migrations** - Controle de versão do banco com Flyway
- 📊 **Relacionamentos Complexos** - Modelagem de dados acadêmicos

### ✨ Funcionalidades Principais

- 👨‍🎓 **Gestão de Alunos** - CRUD completo com histórico acadêmico
- 👨‍🏫 **Gestão de Professores** - Cadastro e controle de docentes
- 📚 **Gestão de Disciplinas** - Criação e gerenciamento de disciplinas
- 🏫 **Gestão de Turmas** - Organização de turmas por período
- 📝 **Sistema de Matrículas** - Controle de matrículas de alunos
- 📊 **Gestão de Notas** - Lançamento e controle de notas
- 🔐 **Autenticação e Autorização** - Sistema seguro com diferentes perfis
- 📄 **Paginação** - Listagens otimizadas
- ✅ **Validações** - Regras de negócio consistentes

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem principal com recursos modernos
- **Spring Boot 3.x** - Framework base
- **Spring Security 6.x** - Autenticação e autorização robusta
- **Spring Data JPA** - Persistência de dados
- **JWT (Auth0)** - Tokens de autenticação stateless
- **Bean Validation** - Validação de dados
- **Lombok** - Redução de boilerplate

### Banco de Dados
- **MySQL 8.0** - Banco de produção
- **Flyway** - Migrations e versionamento

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização da aplicação
- **Git** - Controle de versão

---

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [MySQL 8.0+](https://dev.mysql.com/downloads/mysql/)
- [Docker](https://www.docker.com/get-started) (Opcional)
- [Git](https://git-scm.com/downloads)

---

## 🔧 Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone https://github.com/MarceloNobrega29/gestao-academica.git
cd gestao-academica
```

### 2. Configure o Banco de Dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE gestao_academica;
```

### 3. Configure as Variáveis de Ambiente

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_academica
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JWT Secret
api.security.token.secret=sua_chave_secreta_jwt
```

### 4. Execute as Migrations

As migrations do Flyway serão executadas automaticamente ao iniciar a aplicação.

### 5. Compile e Execute

```bash
# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 🐳 Executando com Docker

### Usando Docker Compose (Recomendado)

Crie um arquivo `docker-compose.yml` na raiz do projeto:

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: gestao-academica-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: gestao_academica
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
    networks:
      - academica-network

  app:
    build: .
    container_name: gestao-academica-api
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: prod
      DATASOURCE_URL: jdbc:mysql://mysql:3306/gestao_academica
      DATASOURCE_USERNAME: root
      DATASOURCE_PASSWORD: root
    depends_on:
      - mysql
    networks:
      - academica-network

volumes:
  mysql-data:

networks:
  academica-network:
    driver: bridge
```

Execute com:

```bash
# Iniciar todos os serviços
docker-compose up -d

# Ver logs
docker-compose logs -f app

# Parar serviços
docker-compose down
```

### Criar Dockerfile

Crie um arquivo `Dockerfile` na raiz do projeto:

```dockerfile
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 📚 Documentação da API

### Autenticação

#### Cadastrar Usuário
```http
POST /auth/cadastro
Content-Type: application/json

{
  "login": "usuario@escola.com",
  "senha": "senha123",
}
```

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "login": "usuario@escola.com",
  "senha": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Endpoints Protegidos

> **⚠️ Nota:** Todos os endpoints abaixo requerem autenticação via Bearer Token

**Header necessário:**
```http
Authorization: Bearer {seu_token_jwt}
```
---

## 🗂️ Estrutura do Projeto


---

## 🗄️ Modelagem do Banco de Dados

### Principais Entidades

- **Aluno** - Dados pessoais e histórico
- **Professor** - Informações dos docentes
- **Disciplina** - Conteúdo e carga horária
- **Turma** - Organização das aulas
- **Matricula** - Vínculo aluno-turma
- **Nota** - Avaliações dos alunos
- **Usuario** - Autenticação do sistema



---

## 🔐 Segurança

### Configuração do Spring Security

O projeto implementa segurança robusta baseada em JWT utilizando **Spring Security 6.x**:

- **Endpoints Públicos:** `/auth/login`, `/auth/cadastro`
- **Endpoints Protegidos:** Todos os demais requerem autenticação
- **Filtro de Segurança:** `SecurityFilter` valida tokens JWT em cada requisição
- **Criptografia:** BCrypt para hash de senhas
- **Stateless:** Sessões não são mantidas no servidor

### Perfis de Usuário

- **ADMIN** - Acesso total ao sistema
- **PROFESSOR** - Acesso a turmas e notas
- **ALUNO** - Acesso limitado aos próprios dados

### Arquitetura de Segurança

```
Cliente → SecurityFilter → Validação JWT → Controller → Service → Repository
           ↓ (se inválido)
         HTTP 403 Forbidden
```

### Geração de Token JWT

```java
// Token válido por 2 horas
// Algoritmo: HMAC256
// Claim: login e role do usuário
```

---

## 🧪 Testes

Execute os testes com:

```bash
# Todos os testes
mvn test

# Testes específicos
mvn test -Dtest=AlunoControllerTest

# Relatório de cobertura
mvn jacoco:report
```

---

## 📊 Deploy em Produção

### Gerar JAR

```bash
mvn clean package -DskipTests
```

O JAR será gerado em: `target/api-0.0.1-SNAPSHOT.jar`

### Executar em Produção

```bash
java -Dspring.profiles.active=prod \
     -DDATASOURCE_URL=jdbc:mysql://localhost:3306/gestao_academica \
     -DDATASOURCE_USERNAME=usuario \
     -DDATASOURCE_PASSWORD=senha \
     -jar target/api-0.0.1-SNAPSHOT.jar
```

---

## 🐛 Troubleshooting

### Erro 403 Forbidden

Verifique se o endpoint está liberado no `SecurityConfiguration`:

```java
// .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
```

### Erro de Conexão com Banco

Confirme se o MySQL está rodando e as credenciais estão corretas:

```bash
mysql -u root -p
SHOW DATABASES;
```

### Token JWT Inválido

Verifique:
- Token expirado (válido por 2 horas)
- Secret key configurada corretamente
- Header `Authorization: Bearer {token}`

### Erro de Constraint em Matrículas

Certifique-se de que:
- O aluno existe no banco
- A turma existe e tem vagas disponíveis
- O aluno não está matriculado na mesma turma

---

## 📝 Boas Práticas Implementadas

✅ **Clean Code** - Código limpo e legível  
✅ **SOLID** - Princípios de design orientado a objetos  
✅ **DTOs** - Separação entre entidades e dados de transferência  
✅ **Repository Pattern** - Abstração da camada de dados  
✅ **Service Layer** - Lógica de negócio isolada  
✅ **Exception Handling** - Tratamento centralizado de erros  
✅ **Validations** - Validações em múltiplas camadas  
✅ **Security Best Practices** - Autenticação e autorização robustas  
✅ **Database Normalization** - Modelagem normalizada  
✅ **Soft Delete** - Exclusão lógica de registros

---



## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Add: Nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

---


## 👨‍💻 Autor

**Marcelo Nobrega**

- GitHub: [@MarceloNobrega29](https://github.com/MarceloNobrega29)
- LinkedIn: [Marcelo Nobrega](https://www.linkedin.com/in/marcelo-n%C3%B3brega-8046752ba/)

---

## 🎓 Informações Acadêmicas

**Disciplina:** Tecnologia para Back-End Avançado  
**Professor:** Antonio Junio Figueiredo da Mata  
**Instituição:** UNIESP - Cabedelo  
**Período:** 4° Período

### 📚 Conceitos Abordados

- Spring Security e autenticação JWT
- Arquitetura RESTful
- Persistência com JPA/Hibernate
- Relacionamentos complexos em banco de dados
- Migrations com Flyway
- Validações e tratamento de exceções
- Boas práticas de desenvolvimento back-end
- Modelagem de domínio acadêmico

---

## 📧 Contato

Para dúvidas ou sugestões, entre em contato:

- Email: nobregamf29hotmail.com
- Issues: [GitHub Issues](https://github.com/MarceloNobrega29/gestao-academica/issues)

---

<div align="center">


⭐ Se este projeto te ajudou, considere dar uma estrela!

</div>