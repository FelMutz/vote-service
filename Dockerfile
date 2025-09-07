FROM gradle:8.10-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar --no-daemon

FROM openjdk:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]