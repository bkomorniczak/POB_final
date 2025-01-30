# Etap 1: budowanie
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app


# Kopiujemy cały projekt do kontenera (czyli "context" to folder główny projektu)
COPY . .
RUN ls -lah /app
RUN ls -lah /app/service1
RUN cat /app/settings.gradle.kts || echo "No settings.gradle.kts found"

# Dajemy uprawnienia wykonania skryptowi gradlew (jeśli nie są ustawione)
RUN chmod +x gradlew

# Budujemy JAR tylko modułu service1
RUN ./gradlew :service1:clean :service1:bootJar

# Etap 2: uruchamianie
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/service1/build/libs/service1-*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
