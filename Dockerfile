FROM maven:3.6.0-jdk-11-slim As mavenBuild
COPY src ./src
COPY pom.xml ./
RUN mvn clean package -DskipTests
COPY . .

FROM openjdk:11-jre-slim
COPY --from=mavenBuild /target/event-service-0.0.1-snapshot.jar /base/app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-DPORT=80", "/base/app.jar"]