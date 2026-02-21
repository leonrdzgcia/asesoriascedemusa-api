FROM eclipse-temurin:11-jdk-alpine AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw package -DskipTests -B

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:+UseContainerSupport -XX:+ExitOnOutOfMemoryError -XX:+UseSerialGC"
EXPOSE $PORT
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
