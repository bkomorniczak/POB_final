FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service7:clean :service7:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service7/build/libs/service7-*.jar app.jar

COPY --from=build /app/service7/src/main/resources/application.yml /app/application.yml

EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
