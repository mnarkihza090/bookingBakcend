# ===== Build stage =====
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Cache dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Build
COPY src ./src
RUN mvn -q -DskipTests package

# ===== Runtime stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy fat jar
COPY --from=builder /workspace/target/*.jar /app/app.jar

# Ensure uploads dir exists (served by MvcConfig via file:uploads/)
RUN mkdir -p /app/uploads

EXPOSE 8080

# Allow optional JAVA_OPTS (e.g., -Xms256m -Xmx512m)
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
