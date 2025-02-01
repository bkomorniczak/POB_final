FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service6:clean :service6:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service6/build/libs/service6-*.jar app.jar

COPY --from=build /app/service6/src/main/resources/application.yml /app/application.yml

EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
