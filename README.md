`docker build --tag api:v1.0 ./api`

`docker build --tag internal:v1.0 ./internal`

`docker run --rm -p 8080:8080 -v m2:/root/.m2 --network micro-api --name api api:v1.0`

`docker run --rm -v m2:/root/.m2 --network micro-api --name internal internal:v1.0`

`docker run --rm -e POSTGRES_PASSWORD=123 -v pg:/var/lib/postgresql/data --name docker-pg --network micro-db postgres`

`docker network connect micro-db c9b52e69f5c9`
c9b52e69f5c9 - имя контейнера internal

