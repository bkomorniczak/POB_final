FROM justb4/jmeter:latest

WORKDIR /jmeter

# Kopiujemy pliki testowe
COPY testPlan.jmx /jmeter/testPlan.jmx
COPY entrypoint.sh /entrypoint.sh

# Nadajemy uprawnienia do uruchomienia skryptu
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
