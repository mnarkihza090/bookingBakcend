# Build stage
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy project files
COPY pom.xml ./
COPY src ./src

# Build the application
RUN mvn -B -DskipTests clean package

# Runtime stage
FROM eclipse-temurin:17-jre-jammy AS runtime
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=build /app/target/*.jar /app/app.jar

# Ensure uploads directory exists (also mounted as a volume in compose)
RUN mkdir -p /app/uploads

EXPOSE 8080

# Allow optional JVM flags via JAVA_OPTS
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
