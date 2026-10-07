# Passo a passo: Spring Boot + Postgres no Docker + JWT + CRUD

## Passo 1: subir o Postgres no Docker

Crie a pasta do projeto e, dentro dela, o arquivo compose.yaml:

```yaml
services:
  db:
    image: postgres:17
    container_name: loja-db
    environment:
      POSTGRES_DB: loja
      POSTGRES_USER: loja
      POSTGRES_PASSWORD: loja123
    ports:
      - "5432:5432"
    volumes:
      - loja-dados:/var/lib/postgresql/data

volumes:
  loja-dados:
```

## Teste de conexão com o banco
Suba o banco e confira se está no ar:

```shell
docker compose up -d
docker compose ps
docker exec -it loja-db psql -U loja -d loja -c "select version();"
```
