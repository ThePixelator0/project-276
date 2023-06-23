FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
RUN ls -la  # Add this line to debug the contents of the WORKDIR

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /usr/src/app/target/project-0.0.1-SNAPSHOT.jar project.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","project.jar"]
