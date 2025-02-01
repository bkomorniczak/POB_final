FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew :service8:clean :service8:bootJar

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service8/build/libs/service8-*.jar app.jar

COPY --from=build /app/service8/src/main/resources/application.yml /app/application.yml

EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
