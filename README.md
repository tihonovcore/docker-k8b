### Architecture

![](./.images/arch.png)

### Docker

`docker build --tag api:v1.0 ./api`

`docker build --tag internal:v1.0 ./internal`

`docker run --rm -p 8080:8080 -v m2:/root/.m2 --network micro-api --name api api:v1.0`

`docker run --rm -v m2:/root/.m2 --network micro-api --name internal internal:v1.0`

`docker pull postgres`

`docker run --rm -e POSTGRES_PASSWORD=123 -v pg:/var/lib/postgresql/data --name docker-pg --network micro-db postgres`

```roomsql
create table users(id SERIAL, name text);
```

`docker network connect micro-db c9b52e69f5c9`
c9b52e69f5c9 - имя контейнера internal

`curl --location --request POST 'localhost:8080/user?name=bar'`

`curl --location --request GET  'localhost:8080/user/1'`

`docker rmi api:v1.0`

`docker rmi internal:v1.0`

`docker volume rm m2`

`docker volume rm ps`

### Docker compose

`docker compose up`
