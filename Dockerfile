FROM ubuntu:latest AS build 
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
COPY --from=build /target/moneywrench-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]