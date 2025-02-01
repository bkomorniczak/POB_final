FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service5:clean :service5:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service5/build/libs/service5-*.jar app.jar

COPY --from=build /app/service5/src/main/resources/application.yml /app/application.yml

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
