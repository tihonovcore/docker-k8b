# syntax=docker/dockerfile:1

FROM amazoncorretto:17.0.2

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "mn:run", "-Dmicronaut.environments=prod"]