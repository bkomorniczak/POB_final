FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service10:clean :service10:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service10/build/libs/service10-*.jar app.jar

COPY --from=build /app/service10/src/main/resources/application.yml /app/application.yml

EXPOSE 8099
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
