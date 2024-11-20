FROM eclipse-temurin:17 AS builder
WORKDIR /Users/lee/Documents/workspace/catalog-service
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar
RUN java -Djarmode=layertools -jar catalog-service.jar extract

FROM eclipse-temurin:17
RUN useradd spring
USER spring
WORKDIR /Users/lee/Documents/workspace/catalog-service
COPY --from=builder /Users/lee/Documents/workspace/catalog-service/dependencies/ ./
COPY --from=builder /Users/lee/Documents/workspace/catalog-service/spring-boot-loader/ ./
COPY --from=builder /Users/lee/Documents/workspace/catalog-service/snapshot-dependencies/ ./
COPY --from=builder /Users/lee/Documents/workspace/catalog-service/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]