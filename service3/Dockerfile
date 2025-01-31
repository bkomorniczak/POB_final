# Etap 1: budowanie
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Kopiujemy cały projekt do kontenera (czyli "context" to folder główny projektu)
COPY . .

# Dajemy uprawnienia wykonania skryptowi gradlew (jeśli nie są ustawione)
RUN chmod +x gradlew

# Budujemy JAR tylko modułu service2
RUN ./gradlew :service3:clean :service3:bootJar

# Etap 2: uruchamianie
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service3/build/libs/service3-*.jar app.jar

# ✅ Kopiujemy application.yml do katalogu, w którym Spring Boot go znajdzie
COPY --from=build /app/service3/src/main/resources/application.yml /app/application.yml

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]
