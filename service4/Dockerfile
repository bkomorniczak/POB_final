FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service4:clean :service4:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service4/build/libs/service4-*.jar app.jar

COPY --from=build /app/service4/src/main/resources/application.yml /app/application.yml

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
