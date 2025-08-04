# API GraphQL - Java 21

Uma API GraphQL simples para aprendizado, construída com Spring Boot 3.2 e Java 21.

## Funcionalidades

- **Queries**: Buscar livros e autores
- **Mutations**: Criar, atualizar e deletar livros e autores
- **Relacionamentos**: Livros têm autores, autores têm livros
- **GraphiQL**: Interface web para testar a API

## Como Executar

### Pré-requisitos
- Java 21
- Maven 3.6+

### Executando a aplicação

```bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em:
- **API GraphQL**: http://localhost:8080/graphql
- **GraphiQL Interface**: http://localhost:8080/graphiql

## Exemplos de Queries

### Buscar todos os livros
```graphql
query {
  allBooks {
    id
    title
    isbn
    pageCount
    author {
      id
      fullName
      email
    }
  }
}
```

### Buscar livro por ID
```graphql
query {
  bookById(id: "1") {
    id
    title
    isbn
    pageCount
    author {
      fullName
    }
  }
}
```

### Buscar todos os autores
```graphql
query {
  allAuthors {
    id
    firstName
    lastName
    fullName
    email
    books {
      id
      title
    }
  }
}
```

### Buscar livros por autor
```graphql
query {
  booksByAuthor(authorId: "1") {
    id
    title
    pageCount
  }
}
```

## Exemplos de Mutations

### Criar um novo autor
```graphql
mutation {
  createAuthor(input: {
    firstName: "Kent"
    lastName: "Beck"
    email: "kent@tdd.com"
  }) {
    id
    fullName
    email
  }
}
```

### Criar um novo livro
```graphql
mutation {
  createBook(input: {
    title: "Test Driven Development"
    isbn: "978-0321146533"
    pageCount: 240
    authorId: "4"
  }) {
    id
    title
    isbn
    pageCount
    author {
      fullName
    }
  }
}
```

### Atualizar um livro
```graphql
mutation {
  updateBook(id: "1", input: {
    title: "Clean Code - Updated"
    isbn: "978-0132350884"
    pageCount: 500
    authorId: "1"
  }) {
    id
    title
    pageCount
  }
}
```

### Deletar um livro
```graphql
mutation {
  deleteBook(id: "1")
}
```

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/graphql/
│   │   ├── GraphqlApiApplication.java     # Classe principal
│   │   ├── controller/
│   │   │   └── GraphQLController.java     # Resolvers GraphQL
│   │   ├── model/
│   │   │   ├── Author.java                # Modelo Author
│   │   │   └── Book.java                  # Modelo Book
│   │   └── service/
│   │       └── BookService.java           # Serviço de dados
│   └── resources/
│       ├── application.yml                # Configurações
│       └── graphql/
│           └── schema.graphqls            # Schema GraphQL
└── test/
```

## Conceitos GraphQL Demonstrados

1. **Schema Definition**: Definição de tipos, queries e mutations
2. **Resolvers**: Implementação das operações GraphQL
3. **Field Resolvers**: Resolução de campos relacionados
4. **Input Types**: Tipos de entrada para mutations
5. **Relacionamentos**: Navegação entre entidades
6. **GraphiQL**: Interface para explorar e testar a API

## Dados Iniciais

A aplicação vem com dados pré-carregados:

**Autores:**
- Robert Martin (Clean Code, Clean Architecture)
- Martin Fowler (Refactoring)
- Joshua Bloch (Effective Java)

**Livros:**
- Clean Code
- Clean Architecture
- Refactoring
- Effective Java

## Para Entrevistas

Esta API demonstra conhecimento em:
- GraphQL Schema Design
- Spring Boot 3.x
- Java 21
- Resolvers e Field Resolvers
- Queries e Mutations
- Relacionamentos entre entidades
- Configuração e estrutura de projeto