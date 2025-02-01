FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service9:clean :service9:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service9/build/libs/service9-*.jar app.jar

COPY --from=build /app/service9/src/main/resources/application.yml /app/application.yml

EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
