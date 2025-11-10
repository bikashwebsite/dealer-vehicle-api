# Stage 1: build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom and source
COPY pom.xml .
COPY src ./src

# build jar (skip tests to speed up; change if you want tests)
RUN mvn -B clean package -DskipTests

# Stage 2: runtime image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# expose a port (optional, Render will use PORT env)
EXPOSE 9075

# run
ENTRYPOINT ["java", "-jar", "app.jar"]
