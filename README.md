# Ustaw opóźnienie na 2000ms (2 sekundy)
curl -X POST "http://localhost:8081/chaos/delay?delayMillis=2000"

# Włącz sztuczne przeciążenie
curl -X POST "http://localhost:8081/chaos/overload?overloadActive=true"

# Wyłącz przeciążenie
curl -X POST "http://localhost:8081/chaos/overload?overloadActive=false"

# Zmiana algorytmu w runtime
curl -X POST "http://localhost:8081/strategy/change?newStrategy=LEADER"


# uruchom aplikacje
docker-compose up --build

# Grafana
localhost:3000
# Opis Aplikacji

## Wprowadzenie
Aplikacja jest zbudowana przy użyciu języka Java oraz frameworka Spring Boot. Projekt wykorzystuje Gradle jako system budowania oraz Kotlin jako dodatkowy język programowania. Aplikacja składa się z kilku modułów, w tym `communication-lib`, `service1`, `service2` oraz `service3`, które realizują różne funkcjonalności.

## Moduł `communication-lib`

### Opis
Moduł `communication-lib` zawiera wspólne komponenty do komunikacji między usługami. Zawiera interfejsy i klasy implementujące różne strategie komunikacji.

### Klasa `CommunicationStrategy`
Interfejs definiujący metodę do wysyłania wiadomości:
```java
public interface CommunicationStrategy {
  void sendMessage(String message);
}
```

### Klasa `DefaultCommunicationStrategy`
Implementacja domyślnej strategii komunikacji:
```java
public class DefaultCommunicationStrategy implements CommunicationStrategy {
  @Override
  public void sendMessage(String message) {
    // Implementacja wysyłania wiadomości
  }
}
```

## Moduł `service1`

### Opis
Moduł `service1` zawiera mechanizmy do symulacji chaosu w aplikacji oraz wysyłania automatycznych wiadomości.

### Konfiguracja Chaosu
#### Plik konfiguracyjny `application.yml`
Plik `application.yml` znajduje się w ścieżce `service1/src/main/resources/application.yml` i zawiera konfiguracje aplikacji, w tym nazwę usługi:
```yaml
spring:
  application:
    name: service1
chaos:
  delayMillis: 1000
  overloadActive: true
```

#### Klasa `ChaosConfig`
Klasa `ChaosConfig` jest odpowiedzialna za przechowywanie ustawień chaosu:
```java
@Configuration
@ConfigurationProperties(prefix = "chaos")
public class ChaosConfig {
  private long delayMillis;
  private boolean overloadActive;

  // Gettery i settery
}
```

#### Klasa `ChaosService`
Klasa `ChaosService` wykorzystuje `ChaosConfig` do symulacji opóźnienia oraz obciążenia CPU:
```java
@Service
public class ChaosService {
  private final ChaosConfig chaosConfig;

  public ChaosService(ChaosConfig chaosConfig) {
    this.chaosConfig = chaosConfig;
  }

  public void simulateChaos() {
    simulateDelay();
    simulateOverload();
  }

  private void simulateDelay() {
    long delay = chaosConfig.getDelayMillis();
    if (delay > 0) {
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private void simulateOverload() {
    if (chaosConfig.isOverloadActive()) {
      // sztuczne obciążenie CPU
      double sum = 0;
      for (int i = 0; i < 2_000_000; i++) {
        sum += Math.sqrt(i);
      }
    }
  }
}
```

#### Klasa `ChaosFilter`
Klasa `ChaosFilter` implementuje filtr, który wywołuje symulację chaosu przed przetworzeniem każdego żądania:
```java
@Component
public class ChaosFilter implements Filter {
  private final ChaosService chaosService;

  public ChaosFilter(ChaosService chaosService) {
    this.chaosService = chaosService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    chaosService.simulateChaos();
    chain.doFilter(request, response);
  }
}
```

#### Kontroler `ChaosController`
Kontroler `ChaosController` umożliwia dynamiczne ustawianie parametrów chaosu za pomocą żądań HTTP:
```java
@RestController
@RequestMapping("/chaos")
public class ChaosController {
  private final ChaosConfig chaosConfig;

  public ChaosController(ChaosConfig chaosConfig) {
    this.chaosConfig = chaosConfig;
  }

  @PostMapping("/delay")
  public String setDelay(@RequestParam long delayMillis) {
    chaosConfig.setDelayMillis(delayMillis);
    return "Ustawiono delayMillis na " + delayMillis;
  }

  @PostMapping("/overload")
  public String setOverload(@RequestParam boolean overloadActive) {
    chaosConfig.setOverloadActive(overloadActive);
    return "Ustawiono overloadActive na " + overloadActive;
  }
}
```

### Automatyczne Wiadomości
#### Klasa `AutomaticMessageService`
Klasa `AutomaticMessageService` wysyła wiadomości z nazwą usługi oraz aktualnym czasem:
```java
@Service
public class AutomaticMessageService {
  private final CommunicationStrategy communicationStrategy;
  private final String serviceName;

  public AutomaticMessageService(CommunicationStrategy communicationStrategy, @Value("${spring.application.name}") String serviceName) {
    this.communicationStrategy = communicationStrategy;
    this.serviceName = serviceName;
  }

  @Scheduled(fixedRate = 5000)
  public void broadcastPeriodicMessage() {
    String message = "Cześć, tu " + serviceName + "! Godzina = " + System.currentTimeMillis();
    communicationStrategy.sendMessage(message);
  }
}
```

## Moduł `service2`

### Opis
Moduł `service2` zarządza konfiguracją komunikacji, w tym listą węzłów oraz strategią komunikacji.

### Konfiguracja Komunikacji
#### Plik konfiguracyjny `application.yml`
Plik `application.yml` znajduje się w ścieżce `service2/src/main/resources/application.yml` i zawiera konfiguracje komunikacji:
```yaml
communication:
  strategy: default
  nodes:
    - node1
    - node2
  leaderAddress: leader.node
```

#### Klasa `CommunicationConfig`
Klasa `CommunicationConfig` jest odpowiedzialna za przechowywanie ustawień komunikacji:
```java
@Configuration
@ConfigurationProperties(prefix = "communication")
public class CommunicationConfig {
  private String strategy;
  private List<String> nodes;
  private String leaderAddress;

  // Gettery i settery
}
```

#### Główna Klasa Aplikacji
Główna klasa aplikacji `Service2Application` uruchamia aplikację Spring Boot i włącza właściwości konfiguracyjne:
```java
@SpringBootApplication
@EnableConfigurationProperties(CommunicationConfig.class)
public class Service2Application {
  public static void main(String[] args) {
    SpringApplication.run(Service2Application.class, args);
  }
}
```

## Moduł `service3`

### Opis
Moduł `service3` zawiera dodatkowe funkcjonalności, które mogą być specyficzne dla danego projektu.

### Przykładowa Konfiguracja
#### Plik konfiguracyjny `application.yml`
Plik `application.yml` znajduje się w ścieżce `service3/src/main/resources/application.yml` i zawiera 
konfiguracje specyficzne dla `service3`:
```yaml
service3:
  featureEnabled: true
```

#### Klasa `Service3Config`
Klasa `Service3Config` jest odpowiedzialna za przechowywanie ustawień specyficznych dla `service3`:
```java
@Configuration
@ConfigurationProperties(prefix = "service3")
public class Service3Config {
  private boolean featureEnabled;

  // Gettery i settery
}
```

#### Główna Klasa Aplikacji
Główna klasa aplikacji `Service3Application` uruchamia aplikację Spring Boot i włącza właściwości
konfiguracyjne:
```java
@SpringBootApplication
@EnableConfigurationProperties(Service3Config.class)
public class Service3Application {
  public static void main(String[] args) {
    SpringApplication.run(Service3Application.class, args);
  }
}
```

## Podsumowanie
Aplikacja składa się z czterech głównych modułów: `communication-lib`, `service1`, `service2` oraz `service3`, 
które realizują różne funkcjonalności związane z komunikacją, symulacją chaosu oraz specyficznymi funkcjami 
dla danego projektu. Każdy moduł posiada własne klasy konfiguracyjne, serwisy oraz kontrolery,
które umożliwiają dynamiczne zarządzanie ustawieniami aplikacji.