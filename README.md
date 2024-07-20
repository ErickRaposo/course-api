# API para cadastrar cursos

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Esse projeto é uma API feita com **Java, Java Spring, Flyway Migrations, PostgreSQL para o banco de dados, e Spring Security e JWT para o controle de autenticação.**

Essa API foi criada com o objetivo de estudar autenticação e autorização em aplicações Spring, utilizando Spring Security e JWT

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] Criar o CRUD
- [x] Adicionar autenticação e verificação
- [ ] Criar exceptions personalizadas

## índice

- [Instalação](#instalação)
- [Uso](#uso)
- [API Endpoints](#api-endpoints)
- [Autenticação](#autenticação)
- [Banco de Dados](#banco-de-dados)

## Pré-requisitos

- Java 21 ou superior
- Maven
- PostgreSQL

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/ErickRaposo/course-api.git
```

2. Instale as dependências com o Maven

3. Instale [PostgreSQL](https://www.postgresql.org/)

## Uso

1. Inicie a aplicação com o Maven
2. A API estará acessível em http://localhost:8080


## API Endpoints
A API possui os seguintes endpoints:

#### GET /course - Retorna a lista dos cursos do usuário autenticado. (Apenas usuários autenticados)

#### POST /course - Adiciona um novo curso ao usuário. (Apenas usuários autenticados)

Exemplo do body:
```json
{
    "name": "React",
    "category": "Front-end",
    "teacher": "Fulano"
}

```

#### PUT /course/{id} - Edita o curso especificado pelo ID. (Apenas usuários autenticados)
Exemplo do body:
```json
{
    "name": "Java",
    "category": "Back-end",
    "teacher": "Siclano"
}
```

#### DELETE /course/{id} - Remove o curso especificado pelo ID.

#### POST /auth/login - Faça o login na aplicação.

#### POST /auth/register - Cadastra um novo usuário na aplicação.

## Autenticação
Essa API usa Spring Security para o controle de autenticação. Para acessar endpoints protegidos, forneça credenciais de autenticação no cabeçalho da requisição.


## Banco de Dados
O projeto utiliza [PostgreSQL](https://www.postgresql.org/) como banco de dados. As migration necessárias são gerenciadas pelo FlyWay.